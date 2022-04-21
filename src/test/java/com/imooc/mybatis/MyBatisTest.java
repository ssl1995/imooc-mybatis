package com.imooc.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.imooc.mybatis.dto.GoodsDto;
import com.imooc.mybatis.entity.Goods;
import com.imooc.mybatis.entity.GoodsDetail;
import com.imooc.mybatis.utils.MybatisUtils;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/4/12 00:20
 * @description
 */
public class MyBatisTest {


    @Test
    public void sqlSessionFactoryTest() {
        SqlSession sqlSession = null;
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sqlSessionFactory.openSession();

            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                // 如果dataSource type="POOLED"，代表使用连接池，close则是将连接回收到连接池里
                // 如果dataSource type="UNPOOLED"，代表直连，close则是将连接关闭
                sqlSession.close();
            }
        }

    }

    @Test
    public void MyBatisUtilsTest() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();

            List<Goods> list = sqlSession.selectList("goods.selectAll");

            for (Goods goods : list) {
                System.out.println(goods.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }

    @Test
    public void MyBatisUtilsTest1() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();

            Goods goods = sqlSession.selectOne("goods.selectById", 1602);
            System.out.println(goods);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }

    @Test
    public void MyBatisUtilsTest2() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            Map<String, Object> map = new HashMap<>();
            map.put("min", 100);
            map.put("max", 500);
            map.put("limit", 10);

            // 可以不用写namespace，但是必须保证名字id唯一
            List<Goods> selectByPriceMap = sqlSession.selectList("selectByPriceMap", map);

            for (Goods goods : selectByPriceMap) {
                System.out.println(goods);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }


    @Test
    public void MyBatisUtilsTest3() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();

            // 可以不用写namespace，但是必须保证名字id唯一
            List<Map<String, Object>> res = sqlSession.selectList("selectGoodsMap");

            for (Map<String, Object> goods : res) {
                System.out.println(goods);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }

    @Test
    public void MyBatisUtilsTest4() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();

            List<GoodsDto> res = sqlSession.selectList("selectGoodsDTO");

            for (GoodsDto goods : res) {
                System.out.println(goods.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }

    @Test
    public void insertTest1() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            Goods newGood = new Goods();
            newGood.setTitle("测试title");
            newGood.setSubTitle("测试子title");
            newGood.setOriginalCost(200.00f);
            newGood.setCurrentPrice(150.00f);
            newGood.setDiscount(0.75f);
            newGood.setIsFreeDelivery(1);
            newGood.setCategoryId(43);
            // 单条插入成功，返回1
//            int result = sqlSession.insert("insertGood1", newGood);
            int result = sqlSession.insert("insertGood2", newGood);

            // 新增成功需要commit
            sqlSession.commit();
            System.out.println(result);
            System.out.println("----");
            // 回填主键id到实体类指定主键，可通过配置xml标签
            System.out.println(newGood.getGoodsId());

        } catch (Exception e) {
            if (sqlSession != null) {
                // 新增失败，需要回滚
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }

    @Test
    public void updateTest() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            Goods goods = sqlSession.selectOne("selectById", 2676);
            goods.setTitle("修改后的title");
            int result = sqlSession.update("updateOne", goods);

            // 新增成功需要commit
            sqlSession.commit();
            System.out.println(result);
            System.out.println("----");
            System.out.println(goods.getTitle());

        } catch (Exception e) {
            if (sqlSession != null) {
                // 新增失败，需要回滚
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }

    @Test
    public void deleteTest() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            int result = sqlSession.delete("deleteById", 2676);

            // 新增成功需要commit
            sqlSession.commit();
            System.out.println(result);

        } catch (Exception e) {
            if (sqlSession != null) {
                // 新增失败，需要回滚
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }

    @Test
    public void sqlAttackTest() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            Map<String, Object> param = new HashMap<>();
            // #{vlaue}:是将拼接语句作为一个字符串回填，无sql注入风险
            // 原sql：select * from t_goods where title =' '' or 1=1 or title='测试title1' '
//            param.put("title", "测试title1");z

            // ${vlaue}:是原封不动将字符串回填到sql中，有sql注入风险
            // 原sql：select * from t_goods where '' or 1=1 or title='测试title1'
            param.put("title", "'' or 1=1 or title='测试title1' ");
            List<Goods> list = sqlSession.selectList("selectOfSqlAttack", param);

            // 新增成功需要commit
            sqlSession.commit();
            list.forEach(v -> System.out.println(v.getTitle()));

        } catch (Exception e) {
            if (sqlSession != null) {
                // 新增失败，需要回滚
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }

    @Test
    public void oneToManyTest() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            Goods goods = sqlSession.selectOne("selectOneToMany", Goods.class);

            System.out.println(goods.getGoodsDetails().size());
        } catch (Exception e) {
            if (sqlSession != null) {
                // 新增失败，需要回滚
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }


    @Test
    public void manyToOneTest() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            List<GoodsDetail> selectManyToOne = sqlSession.selectList("selectManyToOne", GoodsDetail.class);
            selectManyToOne.forEach(v -> System.out.println(v.getGoods()));
        } catch (Exception e) {
            if (sqlSession != null) {
                // 新增失败，需要回滚
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }

    @Test
    public void pageHelperTest() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            int pageNum = 2;
            int pageSize = 10;
            PageHelper.startPage(pageNum, pageSize);

            Page<Goods> selectManyToOne = (Page) sqlSession.selectList("goods.selectPage");
            System.out.println("当前页码：" + selectManyToOne.getPageNum());
            System.out.println("总页数：" + selectManyToOne.getPages());
            System.out.println("总记录数：" + selectManyToOne.getTotal());
            System.out.println("开始行号：" + selectManyToOne.getStartRow());
            System.out.println("结束行号：" + selectManyToOne.getEndRow());
            System.out.println("当前数据：" + selectManyToOne.getResult());
        } catch (Exception e) {
            if (sqlSession != null) {
                // 新增失败，需要回滚
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            MybatisUtils.closeSqlSession(sqlSession);
        }

    }
}
