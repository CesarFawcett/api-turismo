package practica.turismo.service;

import org.springframework.stereotype.Service;
import practica.turismo.exception.ApiException;

@Service
public class GeoService {

    public void validateGeo(Double lat, Double lon) {
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            throw new ApiException("Coordenadas geográficas inválidas.");
        }
    }
}
