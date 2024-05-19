package com.project.DeliverySystem;

import com.project.DeliverySystem.model.DeliveryGraphWithMap;
import com.project.DeliverySystem.model.interfaces.Graph;
import com.project.DeliverySystem.store.DeliveryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
@ComponentScan({"com.project.DeliverySystem.*"})
public class DeliverySystemApplication {

	@Autowired
	static DeliveryStore deliveryStore;
	public static void main(String[] args) {
		SpringApplication.run(DeliverySystemApplication.class, args);
//		init();
	}

//	public static void init(){
//		Scanner sc = new Scanner(System.in);
//		int v = sc.nextInt();
//		Graph graph = new DeliveryGraphWithMap(v);
//		int edges = sc.nextInt();
//		Set<String> restaurants = new HashSet<>();
//
//		for(int i=0;i<edges;i++){
//			int x = sc.nextInt();
//			int y = sc.nextInt();
//			int weight = sc.nextInt();
//			int t1 = sc.nextInt();
//			int t2 = sc.nextInt();
//			String type1="";
//			String type2="";
//			if(t1==1)
//				type1 = "R";
//			else
//				type1 = "C";
//			if(t2==1)
//				type2 = "R";
//			else
//				type2 = "C";
//			graph.addEdge(x, y, weight, type1, type2);
//			if(type1.equals("R")){
//				String restaurantName = String.valueOf(x)+"R";
//				restaurants.add(restaurantName);
//			}
//		}
//		deliveryStore.setRestaurants(restaurants);
//	}

}
