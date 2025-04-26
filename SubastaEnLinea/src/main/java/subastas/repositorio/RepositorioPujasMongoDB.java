package subastas.repositorio;


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


import subastas.modelo.Puja;
import repositorio.Repositorio;
import repositorio.RepositorioMongoDB;
import utils.PropertiesReader;

@Singleton(name = "RepositorioPujas")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Startup
@Lock(LockType.READ)
public class RepositorioPujasMongoDB extends RepositorioMongoDB<Puja> implements Repositorio<Puja, String>{

	private MongoCollection<Puja> pujas;

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

			pujas = database.getCollection("pujas", Puja.class).withCodecRegistry(defaultCodecRegistry);

		} catch (Exception e) {

		}
	}


	
	@Override
	public MongoCollection<Puja> getCollection() {
		return pujas;
	}

}
