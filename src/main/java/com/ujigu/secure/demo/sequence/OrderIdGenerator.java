package com.ujigu.secure.demo.sequence;

import com.ujigu.secure.common.utils.RandomUtil;

import io.shardingjdbc.core.keygen.KeyGenerator;

public class OrderIdGenerator implements KeyGenerator{
	
	public OrderIdGenerator() {
	}

	@Override
	public Number generateKey() {
		return RandomUtil.rand(10000);
	}
	
}
