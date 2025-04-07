package io.github.gitagliaudyte.estamate.mybatis.dao;

import io.github.gitagliaudyte.estamate.mybatis.model.Property;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface PropertyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PROPERTY
     *
     * @mbg.generated Mon Apr 07 12:41:48 EEST 2025
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PROPERTY
     *
     * @mbg.generated Mon Apr 07 12:41:48 EEST 2025
     */
    int insert(Property row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PROPERTY
     *
     * @mbg.generated Mon Apr 07 12:41:48 EEST 2025
     */
    Property selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PROPERTY
     *
     * @mbg.generated Mon Apr 07 12:41:48 EEST 2025
     */
    List<Property> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PROPERTY
     *
     * @mbg.generated Mon Apr 07 12:41:48 EEST 2025
     */
    int updateByPrimaryKey(Property row);
}