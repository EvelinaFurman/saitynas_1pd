package lt.viko.eif.efurmanova.spring.app.repository;

import lt.viko.eif.efurmanova.spring.app.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}