package bookle.client;

import bookle.modelo.Actividad;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookleRestClient {

	 @GET("actividades/{id}")
	 Call<Actividad> getActividad(@Path("id") String id);
}
