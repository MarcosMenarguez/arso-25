package bookle.repositorio;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import bookle.modelo.Actividad;
import repositorio.Repositorio;
import repositorio.RepositorioMongoDB;
import utils.PropertiesReader;

@Singleton(name="RepositorioActividad")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Startup
@Lock(LockType.READ)
public class RepositorioActividadesMongoDB extends RepositorioMongoDB<Actividad> 
implements Repositorio<Actividad, String>{

	private MongoCollection<Actividad> actividades;
	
	@PostConstruct
	public void init() {
		PropertiesReader properties;
		try {
			properties = new PropertiesReader("mongo.properties");

			String connectionString = properties.getProperty("mongouri");

			MongoClient mongoClient = MongoClients.create(connectionString);

			String mongoDatabase = properties.getProperty("mongodatabase");

			MongoDatabase database = mongoClient.getDatabase(mongoDatabase);

			CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(
					MongoClientSettings.getDefaultCodecRegistry(),
					CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

			actividades = database.getCollection("actividades", Actividad.class).withCodecRegistry(defaultCodecRegistry);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public MongoCollection<Actividad> getCollection() {
		return actividades;
	}

}
