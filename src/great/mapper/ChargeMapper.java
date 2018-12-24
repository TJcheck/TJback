package great.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import great.bean.Charge;
import great.bean.Combo;

@Repository
public interface ChargeMapper {
	public List<Charge> queryCharge(Charge charge);
	public int updateReservation(Charge charge);
}
