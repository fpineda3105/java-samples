/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-09 16:48:15
 * @modify date 2020-10-09 16:48:15
 * @desc Product persistence Implementation using Spring JdbcTemplate
 */
package com.fpineda.samples.choreographypattern.adapter.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.fpineda.samples.choreographypattern.core.model.Product;
import com.fpineda.samples.choreographypattern.core.ports.CommitProductStockPort;
import com.fpineda.samples.choreographypattern.core.ports.FetchProductPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ProductPersistenceAdapter implements CommitProductStockPort, FetchProductPort {

    private final JdbcTemplate jdbcTemplate;

    public ProductPersistenceAdapter(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean commit(long productId, int quantity) {
        var sql =
                "UPDATE products set inventory = CASE WHEN (inventory - ? >= 0) THEN (inventory - ?) ELSE inventory END where id = ?";
        return jdbcTemplate.update(sql, quantity, quantity, productId) == 1;                
    }

    @Override
    public Product fetch(long id) {
        var sql = "SELECT * FROM products where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, new ProductMapper());
    }

    public class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Product.builder().id(rs.getLong("id")).name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .inventoryQuantity(rs.getInt("inventory")).price(rs.getDouble("price")).build();
        }

    }

}
