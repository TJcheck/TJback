package great.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import great.bean.Charge;
import great.mapper.ChargeMapper;
@Service
public class ChargeService {
	@Autowired
	public ChargeMapper chargeMapper;
	public List<Charge> queryCharge(Charge charge) {
		return chargeMapper.queryCharge(charge);
	}
	public boolean updateReservation(Charge charge) {
		return chargeMapper.updateReservation(charge)>0;
	}
}
