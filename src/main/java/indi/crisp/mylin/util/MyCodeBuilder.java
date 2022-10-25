package indi.crisp.mylin.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class MyCodeBuilder {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        var map = new HashMap<String, String>();
        map.put("driver", "com.mysql.cj.jdbc.Driver");
        map.put("url", "jdbc:mysql://luzhao.top:3306/lin");
        map.put("username", "root");
        map.put("password", "root");

        //自定义生成的类名
        var names = new HashMap<String, String>();
        names.put("tbldept", "dept");
        names.put("tbldict", "dict");
        names.put("tblemployee", "employee");
        names.put("tblleave", "leave");
        names.put("tbllog", "log");
        names.put("tblnotify", "notify");
        names.put("tblperm", "perm");
        names.put("tblremark", "remark");
        names.put("tblrole", "role");
        names.put("tblroleperm", "roleperm");


       new MyCodeBuilder(DriverManager.getConnection(map.get("url"), map.get("username"), map.get("password")), map)
        .setPath("E:\\Development\\Project\\IDEA\\Java\\mylin\\src\\main\\java")
        .setNameMapper(names)
        .setPackageClass("indi.crisp.mylin.pojo")
        .setPackageInterface("indi.crisp.mylin.dao")
        .setPackageResource("indi.crisp.mylin.resource")
        .startBuilder();
    }

    private Map<String, Map<String,String>> tableClassMapper; //表字段和类名的映射
    private Map<String, String> typeMapper; //用户自定义表字段和类名的映射
    private Map<String, String> tableNameClassNameMapper; //类名和表名映射
    private Map<String, List<String>> importlist;
    private Map<String, String> tablekeys;
    private Map<String, String> userpasswd;

    //包名
    private String path;
    private String packageNameClass;
    private String packageNameInterface;
    private String packageNameResource;

    /**
     * 只有链接对象
     * @param connection
     */
    public MyCodeBuilder(Connection connection) {
        try {
            init(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 有连接对象和账号密码
     * @param connection
     * @param upMap
     */
    public MyCodeBuilder(Connection connection, Map<String,String> upMap) {
        try {
            init(connection);
            userpasswd = upMap;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 初始化代码生成器
     * @param conn
     * @throws SQLException
     */
    private void init(Connection conn) throws SQLException {
        var t = conn.getMetaData();

        var tableMap = new HashMap<String, List<List<String>>>(); //获取表名和所有列名

        tablekeys = new HashMap<String, String>();    //获取表名对应的主键
        getTables(conn, tableMap, tablekeys);

        //准备映射
        var map = createMapper();   //获取字段类型映射
        tableClassMapper = getTablesMapper(tableMap, map);
        if ( null != typeMapper ) {
            for ( var j : tableClassMapper.entrySet() ) {
                for ( var i : typeMapper.entrySet() ) {
                    j.getValue().put(i.getKey(), i.getValue());
                }
            }
        }
        //获取导入包
        importlist = createImport(tableClassMapper);

        //得到的结果
//        System.out.println("表字段和类的属性的映射：" + tableClassMapper);
//        System.out.println("导入包：" + importlist);
//        System.out.println("每张表的主键：" + tablekeys);
    }

    /**
     * 根据配置自定义创建类名
     * @param map
     * @return
     */
    public MyCodeBuilder setTablesClassName(Map<String,String> map) {
        this.tableNameClassNameMapper = map;
        return this;
    }

    /**
     * 获取表中字段的名称
     * @param tableName
     * @return
     */
    private List<String> getFildList(String tableName) {
        var list = new LinkedList<String>();
        for ( var i : tableClassMapper.get(tableName).entrySet() ) {
            list.add(i.getKey());
        }
        return list;
    }

    /**
     * 获取所有表
     * @param conn
     * @param tableMap
     * @param tableMapKey
     * @throws SQLException
     */
    private void getTables(Connection conn, Map<String, List<List<String>>> tableMap, Map<String, String> tableMapKey) throws SQLException {
        var t2 = conn.getMetaData().getTables(conn.getCatalog(), "%", null, new String[]{"TABLE"});
        while ( t2.next() ) {
            var tables = new LinkedList<List<String>>();
            //获取表名
            var tableName = t2.getString("TABLE_NAME");
            tableMap.put(tableName, tables);
            var t3 = conn.getMetaData().getColumns(conn.getCatalog(), null, tableName, null);
            while ( t3.next() ) {
                var arr = new LinkedList<String>();
                //获取列名
                arr.add(t3.getString("COLUMN_NAME"));
                arr.add(t3.getString("TYPE_NAME"));
                tables.add(arr);
            }
            var t4 = conn.getMetaData().getPrimaryKeys(null, null, tableName);
            if ( t4.next() ) {
                //复合主键直接G
                tableMapKey.put(tableName, t4.getString("COLUMN_NAME"));
            }
        }
    }


    /**
     * 获取表对应字段的映射
     * @param tableMap
     * @param maps
     * @return
     */
    private Map<String, Map<String, String>> getTablesMapper(Map<String, List<List<String>>> tableMap, Map<String, String> maps) {
        var map1 = new HashMap<String, Map<String, String>>();
        for ( var i : tableMap.entrySet() ) {
            var map2 = new HashMap<String, String>();
            map1.put(i.getKey(), map2);
            i.getValue().stream().forEach(arr->{
                //根据map表的映射进行修改
                var obj = maps.get(arr.get(1));
                if ( null == obj ) {
                    obj = "String";
                }
                arr.set(1, obj);
                map2.put(arr.get(0), arr.get(1));
            });
        }
        return map1;
    }

    /**
     * 数据库表和java数据类型的映射
     * @return
     */
    private Map<String, String> createMapper() {
        var map = new HashMap<String, String>();
        map.put("INT", "Integer");
        map.put("BIT", "Integer");
        map.put("MEDIUMINT", "Integer");
        map.put("BIGINT", "Long");
        map.put("TINYINT", "Integer");
        map.put("NULL", "Object");
        map.put("INTEGER", "Integer");
        map.put("VARCHAR", "String");
        map.put("VARBINARY", "String");
        map.put("TEXT", "String");
        map.put("ENUM", "String");
        map.put("SET", "String");
        map.put("CHAR", "String");
        map.put("TIMESTAMP", "Timestamp");
        map.put("DATE", "Date");
        map.put("TIME", "Date");
        map.put("YEAR", "Date");
        return map;
    }


    /**
     * 获取你要导入的包
     * @param map
     * @return
     */
    private Map<String, List<String>> createImport(Map<String, Map<String, String>> map) {
        var t = new HashMap<String, List<String>>();
        for ( var i : map.entrySet() ) {
            var set = new TreeSet<String>();
            for ( var j : i.getValue().entrySet() ) {
                switch (j.getValue()) {
                    case "String" : set.add("java.lang.String"); break;
                    case "Integer" : set.add("java.lang.Integer"); break;
                    case "Long" : set.add("java.lang.Lang"); break;
                    case "Object" : set.add("java.lang.Object"); break;
                    case "Timestamp" : set.add("java.sql.Timestamp"); break;
                    case "Date" : set.add("java.util.Date"); break;
                }
            }
            t.put(i.getKey(), new LinkedList<>(set));
        }
        return t;
    }

    /**
     * 小写转大写
     * @param str
     * @return
     */
    private String charBigSmall(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 大写转小写
     * @param str
     * @return
     */
    private String charSmallBig(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 设置生成包名的绝对路径
     * @param path
     * @return
     */
    public MyCodeBuilder setPath(String path) {
        path = URLDecoder.decode(path, StandardCharsets.UTF_8).replace("\\", "/");
        if (null == Paths.get(path)) {
            throw new RuntimeException(path+"路径不存在");
        }
        this.path = path;
        return this;
    }

    /**
     * 自定义表明和实体类的名称映射
     * @return
     */
    public MyCodeBuilder setNameMapper(Map<String, String> mapperName) {
        this.tableNameClassNameMapper = mapperName;
        return this;
    }

    public MyCodeBuilder setTypeMapper(Map<String, String> typeMapper) {
        this.typeMapper = typeMapper;
        return this;
    }

    /**
     * 设置实体类的包名
     * @param name
     * @return
     */
    public MyCodeBuilder setPackageClass(String name) {
        name = name.replace('\\', '.').replace('/', '.');
        this.packageNameClass = name;
        return this;
    }

    /**
     * 设置接口的包名
     * @param name
     * @return
     */
    public MyCodeBuilder setPackageInterface(String name) {
        name = name.replace('\\', '.').replace('/', '.');
        this.packageNameInterface = name;
        return this;
    }

    /**
     * 设置资源文件的包名
     * @param name
     * @return
     */
    public MyCodeBuilder setPackageResource(String name) {
        name.replace('\\', '.').replace('/', '.');
        this.packageNameResource = name;
        return this;
    }

    /**
     * 开始构建代码
     */
    public void startBuilder() {
        for ( var i : tableClassMapper.entrySet() ) {

            if (null != this.packageNameClass){
                createCodeClass(i.getKey());
                createExtends(i.getKey(), "PO");
                createExtends(i.getKey(), "VO");
            }
            if (null != this.packageNameInterface){
                createCodeInterface(i.getKey());
            }
            if (null != this.packageNameResource){
                createMapperXml(i.getKey());
            }
        }

        if (null != this.packageNameResource){
            createMyBatisXml();
        }
        System.out.println("执行完毕，记得刷新项目");
    }

    /**
     * 构造实体类
     * @param tableName
     */
    private void createCodeClass(String tableName) {
        try {
            //获取用户自定义的名称，没有就用表名
            var className = tableName;
            if ( null != this.tableNameClassNameMapper) {
                className = this.tableNameClassNameMapper.get(tableName);
                if ( null == className ) {
                    className = tableName;
                }
            }
            //小写转大写
            className = charSmallBig(className);

            //准备好目录
            var file = new File(path+("/" + packageNameClass.replace('.', '/')));
            file.mkdirs();

            //准备好文件
            var raf = new RandomAccessFile(path+("/" +
                    packageNameClass.replace('.', '/')) + "/" + className + ".java", "rwd");

            if ( raf.length() > 0 ) {
                //文件已存在
                raf.close();
                System.err.println(packageNameClass + "." + className + " 文件已存在, 重新生成请删除");
                return;
            }

            //包名
            raf.write(("package "+ this.packageNameClass +";\n\n").getBytes());
            for ( var i : this.importlist.get(tableName) ) {
                raf.write(("import "+ i +";\n").getBytes());
            }

            //类名
            raf.write(("\npublic class "+ className +" {\n\n").getBytes());

            //字段
            for ( var i : this.tableClassMapper.get(tableName).entrySet() ) {
                raf.write(("\tprotected "+ i.getValue() +" "+ i.getKey() +";\n").getBytes());
            }

            //方法
            raf.write("\n".getBytes());

            //构造器
            //无参构造
            raf.write(("\tpublic " + className + "() {\n").getBytes());
            raf.write(("\t}\n\n").getBytes());

            //全参构造
            raf.write(("\tpublic " + className + "(").getBytes());

            //参数表
            var flag = true;
            for ( var i : this.tableClassMapper.get(tableName).entrySet() ) {
                if ( flag ) {
                    raf.write((i.getValue() + " " + i.getKey()).getBytes());
                    flag = false;
                } else {
                    raf.write((", " + i.getValue() + " " + i.getKey()).getBytes());
                }
            }

            //构造体
            raf.write(") {\n".getBytes());
            for ( var i : this.tableClassMapper.get(tableName).entrySet() ) {
                raf.write(("\t\t this." + i.getKey() + " = " + i.getKey() + ";\n").getBytes() );
            }
            raf.write("\t}\n\n".getBytes());


            //get和set
            for ( var i : this.tableClassMapper.get(tableName).entrySet() ) {
                //get
                raf.write(("\tpublic " + i.getValue() + " get" + charSmallBig(i.getKey()) + "() {\n").getBytes());
                raf.write(("\t\treturn this." + i.getKey() + ";\n").getBytes() );
                raf.write("\t}\n\n".getBytes());

                //set
                raf.write(("\tpublic void set" + charSmallBig(i.getKey()) + "(" + i.getValue() + " " + i.getKey() + ") {\n").getBytes() );
                raf.write(("\t\t this." + i.getKey() + " = " + i.getKey() + ";\n").getBytes() );
                raf.write("\t}\n\n".getBytes());
            }

            raf.write("}\n".getBytes());
            raf.close();

        } catch (FileNotFoundException e) {
            System.err.println("找不到该路径: " + path);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("找不到该文件或没有权限读写: " + path);
            e.printStackTrace();
        }
    }

    /**
     * 生成对应的子类
     * @param tableName
     * @param hz
     */
    private void createExtends(String tableName, String hz) {
        try {
            //获取用户自定义的名称，没有就用表名
            var className = tableName;
            if ( null != this.tableNameClassNameMapper) {
                className = this.tableNameClassNameMapper.get(tableName);
                if ( null == className ) {
                    className = tableName;
                }
            }
            //小写转大写
            className = charSmallBig(className);

            //准备好目录
            var file = new File(path+("/" + (packageNameClass + ".expand").replace('.', '/')));
            file.mkdirs();

            //准备好文件
            var raf = new RandomAccessFile(path+("/" +
                    (packageNameClass + ".expand").replace('.', '/')) + "/" + className + hz + ".java", "rwd");

            //重复的不覆盖
            if ( raf.length() > 0 ) {
                //文件已存在
                raf.close();
                System.err.println(packageNameClass + "." + className + " 文件已存在, 重新生成请删除");
                return;
            }
            //包名
            raf.write(("package "+ this.packageNameClass + ".expand"+";\n\n").getBytes());

            //父类
            raf.write(("import " + this.packageNameClass + "." + className + ";\n").getBytes());
            for ( var i : this.importlist.get(tableName) ) {
                raf.write(("import "+ i +";\n").getBytes());
            }

            //类名
            raf.write(("\npublic class "+ className + hz +" extends "+ className +" {\n\n").getBytes());

            //更改后面生成的
            className = className + hz;

            //方法
            raf.write("\n".getBytes());

            //构造器
            //无参构造
            raf.write(("\tpublic " + className + "() {\n").getBytes());
            raf.write(("\t}\n\n").getBytes());

            //全参构造
            raf.write(("\tpublic " + className + "(").getBytes());

            //参数表
            var flag = true;
            for ( var i : this.tableClassMapper.get(tableName).entrySet() ) {
                if ( flag ) {
                    raf.write((i.getValue() + " " + i.getKey()).getBytes());
                    flag = false;
                } else {
                    raf.write((", " + i.getValue() + " " + i.getKey()).getBytes());
                }
            }

            //构造体
            raf.write(") {\n".getBytes());
            for ( var i : this.tableClassMapper.get(tableName).entrySet() ) {
                raf.write(("\t\t this." + i.getKey() + " = " + i.getKey() + ";\n").getBytes() );
            }
            raf.write("\t}\n\n".getBytes());



            raf.write("}\n".getBytes());
            raf.close();
            //创建一个Book.java
        } catch (FileNotFoundException e) {
            System.err.println("找不到该路径: " + path);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("找不到该文件或没有权限读写: " + path);
            e.printStackTrace();
        }
    }

    /**
     * 创建适用于mybatis的接口
     * @param tableName
     */
    private void createCodeInterface(String tableName) {
        try {

            //获取用户自定义的名称，没有就用表名
            var className = tableName;
            if ( null != this.tableNameClassNameMapper) {
                className = this.tableNameClassNameMapper.get(tableName);
                if ( null == className ) {
                    className = tableName;
                }
            }
            //小写转大写
            className = charSmallBig(className);

            //准备好目录
            var file = new File(path+("/" + packageNameInterface.replace('.', '/')));
            file.mkdirs();

            //准备好文件
            var raf = new RandomAccessFile(path+("/" +
                    packageNameInterface.replace('.', '/')) + "/" + className + "DAO.java", "rwd");

            if ( raf.length() > 0 ) {
                //文件已存在
                raf.close();
                System.err.println(packageNameInterface + "." + className + " 文件已存在, 重新生成请删除");
                return;
            }
            //包名
            raf.write(("package "+ this.packageNameInterface +";\n\n").getBytes());

            //导包
            raf.write("import org.apache.ibatis.annotations.Param;\n".getBytes());
            raf.write("import java.util.List;\n".getBytes());
            raf.write(("import "+ this.packageNameClass + "." + className + ";\n\n").getBytes());

            //接口名称
            raf.write(("public interface "+ className + "DAO {\n\n").getBytes());


            //写方法
            raf.write(("\t" + className + " find"+ className +"ByID(@Param(\"" + this.tablekeys.get(tableName) + "\") int "+ this.tablekeys.get(tableName) +");\n").getBytes());
            raf.write(("\tList<"+ className +"> find"+ className +"List(@Param(\"start\") int start, @Param(\"count\") int count);\n").getBytes());
            raf.write(("\tInteger insert"+ className +"(@Param(\""+ charBigSmall(className) +"\") "+ className +" "+ charBigSmall(className) +");\n").getBytes());
            raf.write(("\tInteger update"+ className +"(@Param(\""+ charBigSmall(className) +"\") "+ className +" "+ charBigSmall(className) +");\n").getBytes());
            raf.write(("\tInteger update"+ className +"Auto(@Param(\""+ charBigSmall(className) +"\") "+ className +" "+ charBigSmall(className) +");\n").getBytes());
            raf.write(("\tInteger delete"+ className +"(@Param(\""+ this.tablekeys.get(tableName) +"\") int "+ this.tablekeys.get(tableName) +");\n").getBytes());
            raf.write(("\tInteger countAll();\n").getBytes());

            //接口结尾
            raf.write("}\n".getBytes());

        } catch (Exception e) {
            throw new RuntimeException("文件创建失败");
        }

    }


    /**
     * 创建接口对应的sqlxml配置
     * @param tableName
     */
    public void createMapperXml (String tableName){
        //主键
        var key = tablekeys.get(tableName);

        String filepath = path;

        // 接口全包名路径
        String interfacepath;
        // 类名
        String claasname;
        // 返回值全包名路径
        String returnname;
        if (null != tableNameClassNameMapper){
            var t = tableNameClassNameMapper.get(tableName);
            if ( null == t ) {
                t = tableName;
            }
            interfacepath = packageNameInterface +"."+ charSmallBig(t);
            claasname = charSmallBig(t);
            returnname = packageNameClass + "." + charSmallBig(t);
        }else {
            interfacepath = packageNameInterface + "." + charSmallBig(tableName);
            claasname = charSmallBig(tableName);
            returnname = packageNameClass +"."+ charSmallBig(tableName);
        }

        filepath = path + "/" + packageNameResource.replace('.', '/');
        var file = new File(filepath);
        file.mkdirs();
        filepath = filepath + "/" + claasname + "Mapper" + ".xml";

        try {
            RandomAccessFile rand = new RandomAccessFile(filepath,"rwd");
            if ( rand.length() > 0 ) {
                //文件已存在
                rand.close();
                System.err.println(packageNameResource + "." + filepath + " 文件已存在, 重新生成请删除");
                return;
            }
            String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!DOCTYPE mapper PUBLIC\n" +
                    "        \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
                    "        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n\n";
            rand.write(str.getBytes());
            rand.write(("<mapper namespace=\""+ interfacepath + "DAO\">\n\n").getBytes());
            rand.write(("\t<select id=\"find"+ charSmallBig(claasname) +"ByID\" resultType=\""+ returnname +"\">\n").getBytes());
            rand.write(("\t\tSELECT").getBytes());
            int index = 0;
            Map<String, String> map = tableClassMapper.get(tableName);
            Set<Map.Entry<String, String>> entries = map.entrySet();
            String s;
            for (var ent : entries){
                s = ent.getKey();
                if (index == 0){
                    rand.write((" "+s).getBytes());
                    index = 1;
                    continue;
                }
                rand.write((", "+s).getBytes());
            }
            rand.write((" FROM "+ tableName +" WHERE "+key+" = #{"+key+"}\n").getBytes());
            rand.write("\t</select>\n\n".getBytes());

            //id查询
            rand.write(("\t<select id=\"find"+ charSmallBig(claasname) +"List\" resultType=\""+ returnname + "\">\n").getBytes());
            rand.write(("\t\tSELECT").getBytes());
            index = 0;
            for (var ent : entries){
                s = ent.getKey();
                if (index == 0){
                    rand.write((" "+s).getBytes());
                    index = 1;
                    continue;
                }
                rand.write((", "+s).getBytes());
            }
            rand.write((" FROM "+ tableName +" LIMIT #{start}, #{count}\n").getBytes());
            rand.write("\t</select>\n\n".getBytes());

            //对象插入
            rand.write(("\t<insert id=\"insert"+ charSmallBig(claasname) +"\" parameterType=\""+returnname+"\">\n").getBytes());
            rand.write(("\t\tINSERT INTO "+ tableName +" (").getBytes());
            index = 0;
            for (var ent : entries){
                s = ent.getKey();
                if (s.equals(key)){
                    continue;
                }
                if (index == 0){
                    rand.write((s).getBytes());
                    index = 1;
                    continue;
                }
                rand.write((", "+s).getBytes());
            }
            rand.write(") values (".getBytes());
            index = 0;
            for (var ent : entries){
                s = ent.getKey();
                if (s.equals(key)){
                    continue;
                }
                if (index == 0){
                    rand.write(("#{"+ charBigSmall(claasname) + "." + s+"}").getBytes());
                    index = 1;
                    continue;
                }
                rand.write((", "+"#{"+ charBigSmall(claasname) + "." + s +"}").getBytes());
            }
            rand.write(")\n\t</insert>\n\n".getBytes());

            //更新
            rand.write(("\t<update id=\"update"+ charSmallBig(claasname) +"\" parameterType=\""+ returnname +"\">\n").getBytes());
            rand.write(("\t\tUPDATE "+ tableName +" SET ").getBytes());
            index = 0;
            for (var ent : entries){
                s = ent.getKey();
                if (s.equals(key)){
                    continue;
                }
                if (index == 0){
                    rand.write((" "+s+" = "+"#{"+charBigSmall(claasname)+"."+s+"}").getBytes());
                    index = 1;
                    continue;
                }
                rand.write((", "+s+" = "+"#{"+charBigSmall(claasname)+"."+s+"}").getBytes());
            }
            rand.write((" WHERE "+key+" = #{"+ charBigSmall(claasname) + "." + key+"}\n").getBytes());
            rand.write("\t</update>\n\n".getBytes());

            //自适应动态sql更新
            rand.write(("\t<update id=\"update"+ charSmallBig(claasname) + "Auto\" parameterType=\""+ returnname + "\">\n").getBytes());
            rand.write(("\t\tUPDATE "+ tableName + "\n").getBytes());
            rand.write(("\t\t<trim prefix=\"SET\" suffixOverrides=\",\">\n").getBytes());
            for (var ent : entries){
                s = ent.getKey();
                if (s.equals(key)){
                    continue;
                }
                rand.write(("\t\t\t<if test=\""+ charBigSmall(claasname)+"."+ s +" != null\">\n").getBytes());
                rand.write(("\t\t\t\t" + s +" = #{"+ charBigSmall(claasname)+"."+ s +"},\n").getBytes());
                rand.write(("\t\t\t</if>\n\n").getBytes());
            }
            rand.write(("\t\t</trim>\n").getBytes());
            rand.write(("\t\tWHERE "+ key +" = #{"+ charBigSmall(claasname) + "." + key +"}\n").getBytes());
            rand.write(("\t</update>\n\n").getBytes());


            //根据id删除
            rand.write(("\t<delete id=\"delete"+ charSmallBig(claasname) +"\">\n").getBytes());
            rand.write(("\t\tDELETE FROM "+ tableName +" WHERE "+key+" = #{"+key+"}\n").getBytes());
            rand.write("\t</delete>\n\n".getBytes());

            //统计总个数
            rand.write(("\t<select id=\"countAll\" resultType=\"java.lang.Integer\">\n").getBytes());
            rand.write(("\t\tSELECT COUNT("+ key +") FROM "+ tableName +";\n").getBytes());
            rand.write(("\t</select>\n\n").getBytes());
            rand.write("</mapper>\n".getBytes());

        } catch (IOException e) {
            throw new RuntimeException("文件创建失败");
        }
    }

    /**
     * 创建MyBatis核心配置文件xml
     */
    private void createMyBatisXml() {

        String fileName = path + "/" + packageNameResource.replace(".","/") + "/mybatis.xml";

        try {
            var raf = new RandomAccessFile(fileName, "rwd");
            if ( raf.length() > 0 ) {
                //文件已存在
                raf.close();
                System.err.println(packageNameResource + "." + raf + " 文件已存在, 重新生成请删除");
                return;
            }
            raf.write(("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n").getBytes());
            raf.write(("<!DOCTYPE configuration PUBLIC\n").getBytes());
            raf.write(("        \"-//mybatis.org//DTD Config 3.0//EN\"\n").getBytes());
            raf.write(("        \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n").getBytes());
            raf.write(("<configuration>\n\n").getBytes());
            raf.write(("\t<settings>\n").getBytes());
            raf.write(("\t\t<setting name=\"logImpl\" value=\"STDOUT_LOGGING\"/>\n").getBytes());
            raf.write(("\t</settings>\n\n").getBytes());
            raf.write(("\t<environments default=\"development\">\n").getBytes());
            raf.write(("\t\t<environment id=\"development\">\n").getBytes());
            raf.write(("\t\t\t<transactionManager type=\"JDBC\"/>\n").getBytes());
            raf.write(("\t\t\t<dataSource type=\"POOLED\">\n").getBytes());
            raf.write(("\t\t\t\t<property name=\"driver\" value=\""+ userpasswd.get("driver")+"\"/>\n").getBytes());
            raf.write(("\t\t\t\t<property name=\"url\" value=\""+ userpasswd.get("url") +"\"/>\n").getBytes());
            raf.write(("\t\t\t\t<property name=\"username\" value=\""+ userpasswd.get("username") +"\"/>\n").getBytes());
            raf.write(("\t\t\t\t<property name=\"password\" value=\""+ userpasswd.get("password") +"\"/>\n").getBytes());
            raf.write(("\t\t\t</dataSource>\n").getBytes());
            raf.write(("\t\t</environment>\n").getBytes());
            raf.write(("\t</environments>\n\n").getBytes());
            raf.write(("\t<mappers>\n").getBytes());

            raf.write(("\t\t<!--<mapper resource=\"mappers/BookMapper.xml\"/>-->\n").getBytes());
            raf.write(("\t</mappers>\n\n").getBytes());
            raf.write(("</configuration>").getBytes());
        } catch (Exception e) {
            throw new RuntimeException("无法创建");
        }
    }


}
