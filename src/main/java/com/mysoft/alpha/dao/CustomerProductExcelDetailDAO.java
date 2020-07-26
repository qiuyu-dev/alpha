package com.mysoft.alpha.dao;

import com.mysoft.alpha.entity.CustomerProductExcelDetail;
import com.mysoft.alpha.entity.CustomerProductExcelMst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerProductExcelDetailDAO extends JpaRepository<CustomerProductExcelDetail, Integer> {
	public void deleteAllByIdIn(Integer[] ids);

	public List<CustomerProductExcelDetail> findCustomerProductExcelDetailsByCpExcelMstOrderByIdAsc(CustomerProductExcelMst customerProductExcelMst);

	public <S extends CustomerProductExcelDetail> S save(S s);


}
