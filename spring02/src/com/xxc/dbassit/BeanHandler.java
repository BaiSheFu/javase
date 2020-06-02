package com.xxc.dbassit;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/** 有点 */
public class BeanHandler<T> implements ResultSetHandler<T> {

    /**
     * 被封装的实体类的字节码对象
     */
    private Class<T> domainClass;

    public BeanHandler(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    /**
     * 结果集封装的具体实现
     * 此类实现的是吧一个结果集rs封装到一个指定的实体类对象中
     * 使用要求：（为了方便）
     * 实体类中的属性必须和表中的列名一致(sql语句查询出来的列名一致)
     */
    public T handle(ResultSet rs) {
        try {
            // 1. 创建一个实体类对象
            T bean = domainClass.newInstance();

            // 2. 判断是否有结果集
            if (rs.next()) {
                // 3. 得到结果集rs中所有的列名
                // 想要得到列名，得先得到结果集的源信息
                ResultSetMetaData rsmd = rs.getMetaData();

                // 得到源信息之后，还需要得到多少列
                int columnCount = rsmd.getColumnCount();

                // 遍历列数
                for (int i = 1; i <= columnCount; i++) {
                    // 得到每列的名称
                    String columnName = rsmd.getColumnName(i);
                    // 列名就是实体类的属性名称，于是集可以使用列名得到实体类中属性的【描述器】
                    PropertyDescriptor pd = new PropertyDescriptor(columnName, domainClass); //定义实体类中定义的描述器
                    // 获取属性的写入方法(set方法)
                    Method method = pd.getWriteMethod();
                    // 获取当前列名所对应的值
                    Object columnValue = rs.getObject(columnName);
                    // 通过执行方法吧得到的值给属性附上
                    method.invoke(bean, columnValue);
                }
            }
            // 返回封装的对象
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}