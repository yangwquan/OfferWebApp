package com.java.learning.mvcdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.learning.mvcdatabase.dao.Offer;
import com.java.learning.mvcdatabase.dao.OfferDao;

@Service("offerService")
public class OfferService {

	private OfferDao offerDao;

	@Autowired
	public void setOfferDao(OfferDao offerDao) {
		this.offerDao = offerDao;
	}
	
	public List<Offer> getCurrent(){		
		return offerDao.getOffers();
	}

	public void create(Offer offer) {
		offerDao.create(offer);
	}
	
}
