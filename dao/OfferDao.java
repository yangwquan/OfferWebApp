package com.java.learning.mvcdatabase.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

@Component("offerDao")
public class OfferDao {
	
	//private JdbcTemplate jdbc;
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public List<Offer> getOffers(){
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		//return jdbc.query("select * from offers where name ='bob'", new RowMapper<Offer>(){
		return jdbc.query("select * from offers", params, new RowMapper<Offer>(){
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));
				return offer;
			}			
		});
	
	}
	
public Offer getOffer(int id){
		
		//MapSqlParameterSource params = new MapSqlParameterSource("name","bob");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		//return jdbc.query("select * from offers where name ='bob'", new RowMapper<Offer>(){
		return jdbc.queryForObject("select * from offers where id =:id", params, new RowMapper<Offer>(){
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));
				return offer;
			}			
		});
	
	}

	public boolean delete(int id){
	
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.update("delete from offers where id =:id", params)==1;

	}
	
	public boolean create(Offer offer){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("insert into offers (name, email, text)values (:name, :email, :text)", params)==1;
	}
	
	public int [] create(List<Offer> offers){
		
		SqlParameterSource [] params =SqlParameterSourceUtils.createBatch(offers.toArray());
		return jdbc.batchUpdate("insert into offers (name, email, text)values (:name, :email, :text)", params);
		
	}
	
	public boolean update(Offer offer){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("update offers set name=:name, email=:email, text=:text where id=:id", params)==1;
	}
	
	
}
