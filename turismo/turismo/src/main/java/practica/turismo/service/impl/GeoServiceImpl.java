package practica.turismo.service.impl;

import org.springframework.stereotype.Service;
import practica.turismo.exception.ApiException;
import practica.turismo.service.GeoService;

@Service
public class GeoServiceImpl implements GeoService {

    @Override
    public void validateGeo(Double lat, Double lon) {
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            throw new ApiException("Coordenadas geográficas inválidas.");
        }
    }
}
