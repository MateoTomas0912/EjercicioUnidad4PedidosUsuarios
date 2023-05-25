package org.example;

import org.hibernate.Session;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Queue;

public class Operaciones {
    //Ejercicio 1
    public static void Ejercicio1(){
        try(Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Pedidos> query = cb.createQuery(Pedidos.class);
            Root<Pedidos> root = query.from(Pedidos.class);

            Predicate userPredicate = cb.equal(root.get("usuario_id"), 1);
            query.where(userPredicate);

            List<Pedidos> resultado = session.createQuery(query).getResultList();
            System.out.println("Ejercicio 1:");
            for (Pedidos pedido:
                    resultado) {
                System.out.println(pedido.getProducto());
            }
        }
    }

    public static void Ejercicio2(){
        try(Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Pedidos> query = cb.createQuery(Pedidos.class);
            Root<Pedidos> root = query.from(Pedidos.class);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse("2023-05-01");
            Date sqlDate = new Date(utilDate.getTime());

            java.util.Date utilDate2 = dateFormat.parse("2023-05-03");
            Date sqlDate2 = new Date(utilDate2.getTime());

            Predicate userPredicate = cb.between(root.get("fecha_compra"), sqlDate, sqlDate2);
            query.where(userPredicate);

            List<Pedidos> pedidosEntreFechas = session.createQuery(query).getResultList();
            System.out.println("Pedidos entre fechas:");
            for (Pedidos pedido : pedidosEntreFechas){
                System.out.println(pedido.getProducto());
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Ejercicio3(){
        try(Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Pedidos> query = cb.createQuery(Pedidos.class);
            Root<Pedidos> root = query.from(Pedidos.class);

            Predicate userPredicate = cb.like(root.get("producto"), "Camisa");
            query.where(userPredicate);

            List<Pedidos> pedidosDeCamisas = session.createQuery(query).getResultList();
            System.out.println("Ejercicio 3");
            for (Pedidos pedido : pedidosDeCamisas){
                System.out.println(pedido.getId());
            }
        }
    }

    public static void Ejercicio4(){
        try(Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Pedidos> query = cb.createQuery(Pedidos.class);
            Root<Pedidos> root = query.from(Pedidos.class);

            Predicate userPredicate = cb.equal(root.get("usuario_id"), 1);
            query.where(userPredicate);

            List<Pedidos> pedidosDeUsuario = session.createQuery(query).getResultList();
            int pedidos = pedidosDeUsuario.size();
            System.out.println("La cantidad de pedidos del primer usuario es "+ pedidos);
        }
    }

    public static void Ejercicio5(){
        try(Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
            Root<Pedidos> root = query.from(Pedidos.class);

            Expression<Integer> numeroMaximo = cb.max(root.get("cantidad"));
            query.select(numeroMaximo);
            Integer cantidadMaxima = session.createQuery(query).getSingleResult();
            System.out.println("La cantidad maxima es " + cantidadMaxima);


            CriteriaQuery<Pedidos> query2 = cb.createQuery(Pedidos.class);
            Root<Pedidos> root2 = query2.from(Pedidos.class);

            Predicate userPredicate = cb.equal(root2.get("cantidad"), cantidadMaxima);
            query2.where(userPredicate);
            List<Pedidos> pedidosConCantMaxima = session.createQuery(query2).getResultList();
            for (Pedidos pedido : pedidosConCantMaxima){
                System.out.println(pedido.getId());
            }
        }
    }

    public static void Ejercicio6(){
        try(Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Usuarios> query = cb.createQuery(Usuarios.class);
            Root<Usuarios> root = query.from(Usuarios.class);

            Predicate userPredicate = cb.equal(root.get("nombre"), "Juan");
            query.where(userPredicate);

            Usuarios usuario = session.createQuery(query).getSingleResult();

            CriteriaQuery<Pedidos> query2 = cb.createQuery(Pedidos.class);
            Root<Pedidos> root2 = query2.from(Pedidos.class);
            Predicate userPredicate2 = cb.equal(root2.get("producto"), "Chaqueta");
            Predicate userPredicate3 = cb.equal(root2.get("producto"), "Blusa");
            Predicate userPredicate4 = cb.equal(root2.get("usuario_id"), usuario.getId());
            Predicate userPredicate5 = cb.or(userPredicate2, userPredicate3);
            Predicate userPredicate6 = cb.and(userPredicate5, userPredicate4);

            query2.where(userPredicate6);
            List<Pedidos> pedidos = session.createQuery(query2).getResultList();

            for (Pedidos pedido : pedidos){
                System.out.println(pedido.getId());
            }
        }
    }

    public static void Ejercicio7(){
        try(Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Pedidos> query = cb.createQuery(Pedidos.class);
            Root<Pedidos> root = query.from(Pedidos.class);

            query.orderBy(cb.asc(root.get("producto")));

            List<Pedidos> pedidosOrdenados = session.createQuery(query).getResultList();

            for (Pedidos pedido : pedidosOrdenados){
                System.out.println(pedido.getProducto());
            }
        }
    }
}
