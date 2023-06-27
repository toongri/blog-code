package toongri.blog.dbcacheproject.app_point;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
class AppPointService implements AddPointUsecase{
    @Override
    public void addPoint(long userId, BigDecimal amount) {

    }
}
