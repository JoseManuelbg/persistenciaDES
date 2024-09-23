package main.java.prueba;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import main.java.model.Cliente;
import main.java.model.LineaPedido;
import main.java.model.Pedido;
import main.java.model.Producto; 

public class Main {

	public static void main(String[] args) {
        UsoBaseDatos uso = new UsoBaseDatos();
        
        Scanner sc = new Scanner(System.in);
        
        int opcion = 0;
        int opcion2 = 0;
        
        while(opcion != 4) {
        	System.out.println("/************************/");
        	System.out.println("1. Pedido");
        	System.out.println("2. Cliente");
        	System.out.println("3. Producto");
        	System.out.println("4. Salir");
        	System.out.println("/************************/");
        	System.out.println();
        	System.out.println("Introduce la opcion: ");
        	
        	//Scanner para la opcion
        	opcion = sc.nextInt();
        	
        	switch (opcion) {
			case 1: {
				
					System.out.println("Que vas a hacer?");
		        	System.out.println("/************************/");
		        	System.out.println("1. Agregar");
		        	System.out.println("2. Modificar");
		        	System.out.println("3. Borrar");
		        	System.out.println("4. Salir");
		        	System.out.println("/************************/");
		        	System.out.println();
		        	System.out.println("Introduce la opcion: ");
					
		        	opcion2 = sc.nextInt();
		        	
					switch (opcion2) {
					case 1: {
						System.out.println("Introduce el codigo: ");
						String cPedido = sc.next();
						
						Pedido pedido = new Pedido(cPedido);
					    uso.persistirPedido(pedido);
						break;
					}
					case 2 :{
						System.out.println("Introduce el codigo a modificar: ");
						String cBuscar = sc.next();
						System.out.println("Introduce el nuevo");
						String cNuevo = sc.next();
						if(uso.actualizarPedido(new Pedido(cNuevo),new Pedido(cBuscar))) {
							System.out.println("Pedido actualizado correctamente");
							
						}
						
						break;
					}
					case 3:{
						System.out.println("Pon el codigo a eliminar: ");
						String eliminarPedido = sc.next();
						Pedido pBuscar = new Pedido(eliminarPedido);
						
						if(uso.eliminarPedido(pBuscar)) {
							System.out.println("Pedido actualizado correctamente");
							
						}else {
							System.out.println("Error en el borrado");
						}
						break;
					}
					default:
						System.out.println("Opcion invalida");
						break;
					}
				
				break;
			
			
			}
			default:
				System.out.println("Opcion invalida");
				break;
        	}
        }
        
       
        
        /*
        // Pedido
        Pedido pedido = new Pedido("p001");
        pedido.setCodigo("001");
        
        //Persistir el Pedido
        uso.persistirPedido(pedido);
         
        
        //Productos
        Producto p1 = new Producto("Raton", "Raton to pro", LocalDate.now(), LocalDate.now().plusMonths(3));
        Producto p2 = new Producto("Teclado", "Teclado gamer", LocalDate.now(), LocalDate.now().plusMonths(3));
        Producto p3 = new Producto("Monitor", "Monitor 4K", LocalDate.now(), LocalDate.now().plusMonths(3));
        
        uso.persistir(p1);
        uso.persistir(p2);
        uso.persistir(p3);
        
    
        // Línea de Pedido
        LineaPedido lineaPedido1 = new LineaPedido(pedido, p1, 2);
        LineaPedido lineaPedido2 = new LineaPedido(pedido, p2, 1);
        LineaPedido lineaPedido3 = new LineaPedido(pedido, p3, 3);
 
        
        uso.persistirLineaPedido(lineaPedido1);
        uso.persistirLineaPedido(lineaPedido2);
        uso.persistirLineaPedido(lineaPedido3);
        
   
        Cliente c1 = new Cliente(pedido, "Adri el cobra", LocalDate.of(2004, 06, 14));
        uso.persistirCliente(c1);
        
      */
          
         
		
     // Initialize SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Producto.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            // Begin transaction
            session.beginTransaction();
            
            // Selección Simple
            // ----------------------------------------
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Producto> cquery = cb.createQuery(Producto.class);
            cquery.select(cquery.from(Producto.class));
            List<Producto> productos = session.createQuery(cquery).getResultList();
            
            System.out.println("Productos obtenidos con selección simple:");
            for (Producto producto : productos) {
                System.out.println("Producto: " + producto.getNombre() + ", Descripción: " + producto.getDescripcion());
            }
            
            // Selección Utilizando Parámetros
            // ----------------------------------------
            long id = 1; // Example ID
            Query<Producto> paramQuery = session.createQuery("from Producto p where p.id = :id", Producto.class);
            paramQuery.setParameter("id", id);
            Producto producto = paramQuery.uniqueResult();
            
            System.out.println("\nProducto obtenido con selección utilizando parámetros:");
            if (producto != null) {
                System.out.println("Producto encontrado: " + producto.getNombre() + ", Descripción: " + producto.getDescripcion());
            } else {
                System.out.println("No se encontró el producto con ID " + id);
            }
            
            // Selección con Varias Condiciones
            // ----------------------------------------
            LocalDate comienzo = LocalDate.of(2023, 1, 1);
            LocalDate fin = LocalDate.of(2024, 12, 31);
            CriteriaQuery<Producto> dateQuery = cb.createQuery(Producto.class);
            Root<Producto> root = dateQuery.from(Producto.class);
            dateQuery.select(root);
            dateQuery.where(
                cb.and(
                    cb.greaterThanOrEqualTo(root.get("fechaAlta"), comienzo),
                    cb.lessThanOrEqualTo(root.get("fechaAlta"), fin)
                )
            );
            Query<Producto> dateParamQuery = session.createQuery(dateQuery);
            List<Producto> dateProductos = dateParamQuery.getResultList();
            
            System.out.println("\nProductos obtenidos con selección con varias condiciones:");
            for (Producto prod : dateProductos) {
                System.out.println("Producto: " + prod.getNombre() + ", Descripción: " + prod.getDescripcion() + ", Fecha de Alta: " + prod.getFechaAlta());
            }
            
            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the session
            session.close();
            factory.close();
        }
    
        
        
}
	}


