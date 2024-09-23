package main.java.prueba;

import org.hibernate.Session;

import main.java.com.hibernate.ConnectionUtil;
import main.java.model.Cliente;
import main.java.model.LineaPedido;
import main.java.model.Pedido;
import main.java.model.Producto;

public class UsoBaseDatos {


	//Crud de Producto

void persistir(Producto producto) {

   // Creamos y comenzamos transacción con base de datos
   Session session = ConnectionUtil.getSessionFactory().openSession();
   session.beginTransaction();

   // Generamos un nuevo producto

   // Persistimos esta entidad
   session.persist(producto);

   // Hacemos commit en base de datos
   session.getTransaction().commit();

   // Liberamos la conexión
   session.close();
   
}

public void obtener() {
   Session session = ConnectionUtil.getSessionFactory().openSession();
   session.beginTransaction();
   
   var producto = session.find(Producto.class, 1L);
   
   assert(producto != null && producto.getId() == 1L);
   
   session.close();
}

public void actualizar(Producto p) {
   Session session = ConnectionUtil.getSessionFactory().openSession();
   session.beginTransaction();
   
   var producto = session.find(Producto.class, p.getId());
   
   //Verifica que el producto actualizado no es nulo
   if(producto != null) {
   producto.setDescripcion(p.getDescripcion());
   producto.setNombre(p.getNombre());
   producto.setFechaAlta(p.getFechaAlta());
   producto.setFechaBaja(p.getFechaBaja());
   }
   session.merge(producto);
   session.getTransaction().commit();
   session.close();
   
}

public void eliminar(Producto p) {

   Session session = ConnectionUtil.getSessionFactory().openSession();

   session.beginTransaction();

   session.remove(session.find(Producto.class, p.getId()));

   session.getTransaction().commit();

   session.close();

}

//Crud de Cliente

public void persistirCliente(Cliente cliente) {
    Session session = ConnectionUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.persist(cliente);
    session.getTransaction().commit();
    session.close();
}

	public void obtenerCliente() {
	   Session session = ConnectionUtil.getSessionFactory().openSession();
	   session.beginTransaction();
	   
	   var producto = session.find(Producto.class, 1L);
	   
	   assert(producto != null && producto.getId() == 1L);
	   
	   session.close();
	}

	public void actualizarCliente(Cliente c) {
	   Session session = ConnectionUtil.getSessionFactory().openSession();
	   session.beginTransaction();
	   
	   var cliente = session.find(Cliente.class, c.getId());
	   
	   //Verifica que el producto actualizado no es nulo
	   if(cliente != null) {
		   cliente.setNombre(c.getNombre());
		   cliente.setFechaNacimiento(c.getFechaNacimiento());
		   cliente.setPedido(c.getPedido());
	   }
	   session.merge(cliente);
	   session.getTransaction().commit();
	   session.close();
	   
	}

	public void eliminarCliente(Cliente c) {

	   Session session = ConnectionUtil.getSessionFactory().openSession();

	   session.beginTransaction();

	   session.remove(session.find(Cliente.class, c.getId()));

	   session.getTransaction().commit();

	   session.close();

	}


	//Crud de Pedido

	void persistirPedido(Pedido pedido) {

		   // Creamos y comenzamos transacción con base de datos
		   Session session = ConnectionUtil.getSessionFactory().openSession();
		   session.beginTransaction();

		   // Generamos un nuevo producto

		   // Persistimos esta entidad
		   session.persist(pedido);

		   // Hacemos commit en base de datos
		   session.getTransaction().commit();

		   // Liberamos la conexión
		   session.close();
		   
		}

		public Pedido obtenerPedido(String codigo) {
		   Session session = ConnectionUtil.getSessionFactory().openSession();
		   session.beginTransaction();
		   
		   Pedido producto = session.find(Pedido.class, codigo);
		   
		   assert(producto != null && producto.getCodigo() == codigo);
		   
		   session.close();
		   return producto;
		}

		public boolean actualizarPedido( Pedido pActualizar, Pedido pBuscar) {
		    boolean res = false;
		    if(pBuscar.getCodigo()!= null) {
		    	System.out.println("entra");
		    	eliminarPedido(pBuscar);
		    	persistirPedido(pActualizar);
		    }
		    return res;
		}

		public boolean eliminarPedido(Pedido pBuscar) {
		    Session session = ConnectionUtil.getSessionFactory().openSession();
		    session.beginTransaction();
		    
		    // Buscar el pedido basado en el código
		    String hql = "FROM Pedido p WHERE p.codigo = :codigo";
		    Pedido pedidoAEliminar = session.createQuery(hql, Pedido.class)
		                                    .setParameter("codigo", pBuscar.getCodigo())
		                                    .uniqueResult();
		    
		    if (pedidoAEliminar != null) {
		        session.remove(pedidoAEliminar);
		        session.getTransaction().commit();
		    } else {
		        session.getTransaction().rollback(); // Hacer rollback si no se encuentra el pedido
		        session.close();
		        return false; // No se encontró el pedido
		    }
		    
		    session.close();
		    return true; // Se eliminó correctamente
		}

		//LineaPedido
		
		// Persistir una instancia de LineaPedido
		void persistirLineaPedido(LineaPedido lineaPedido) {
		    // Creamos y comenzamos la transacción con la base de datos
		    Session session = ConnectionUtil.getSessionFactory().openSession();
		    session.beginTransaction();

		    // Establecemos el pedido asociado a la línea de pedido
		    Pedido pedido = lineaPedido.getPedido();
		    if (pedido != null) {
		        // En lugar de persist, usamos merge para evitar el error de entidad separada (detached)
		        session.merge(pedido);  // Cambiado de persist a merge
		    }

		    // Persistimos la línea de pedido
		    session.persist(lineaPedido);

		    // Hacemos commit en la base de datos
		    session.getTransaction().commit();

		    // Cerramos la conexión
		    session.close();
		}


		// Obtener una instancia de LineaPedido por su ID
		public LineaPedido obtenerLineaPedido(long id) {
		    Session session = ConnectionUtil.getSessionFactory().openSession();
		    session.beginTransaction();

		    LineaPedido lineaPedido = session.find(LineaPedido.class, id);

		    session.close();

		    return lineaPedido;
		}

		// Actualizar una instancia de LineaPedido
		public void actualizarLineaPedido(LineaPedido lineaPedido) {
		    Session session = ConnectionUtil.getSessionFactory().openSession();
		    session.beginTransaction();

		    LineaPedido lineaPedidoActualizada = session.find(LineaPedido.class, lineaPedido.getId());

		    // Verifica que la línea de pedido actualizada no sea nula
		    if (lineaPedidoActualizada != null) {
		        // Actualizamos los campos de la línea de pedido
		        lineaPedidoActualizada.setPedido(lineaPedido.getPedido());
		        lineaPedidoActualizada.setProducto(lineaPedido.getProducto());
		        lineaPedidoActualizada.setCantidad(lineaPedido.getCantidad());
		        lineaPedidoActualizada.setFechaCreacion(lineaPedido.getFechaCreacion());
		    }

		    // Fusionamos los cambios en la sesión y hacemos commit
		    session.merge(lineaPedidoActualizada);
		    session.getTransaction().commit();
		    session.close();
		}

		// Eliminar una instancia de LineaPedido
		public void eliminarLineaPedido(LineaPedido lineaPedido) {
		    Session session = ConnectionUtil.getSessionFactory().openSession();
		    session.beginTransaction();

		    // Eliminamos la instancia de LineaPedido de la base de datos
		    session.remove(session.find(LineaPedido.class, lineaPedido.getId()));

		    // Hacemos commit en la base de datos
		    session.getTransaction().commit();

		    // Cerramos la conexión
		    session.close();
		}

}