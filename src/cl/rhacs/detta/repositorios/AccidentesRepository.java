package cl.rhacs.detta.repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import cl.rhacs.detta.modelos.Accidente;
import cl.rhacs.detta.modelos.database.ConexionDB;
import cl.rhacs.detta.modelos.enums.EClasificacion;
import cl.rhacs.detta.modelos.enums.EPrueba;
import cl.rhacs.detta.modelos.enums.ETipo;
import cl.rhacs.detta.repositorios.interfaces.IRepository;

public class AccidentesRepository implements IRepository {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link ConexionDB} con la conexión a la base de datos */
    private ConexionDB conexion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link AccidentesRepository}
     * 
     * @param url      url de conexión con la base de datos
     * @param username nombre de usuario de acceso a la base de datos
     * @param password contraseña de acceso a la base de datos
     */
    public AccidentesRepository(String url, String username, String password) {
        conexion = ConexionDB.getInstance(url, username, password);
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Extrae la información del error {@link SQLException} y lo muestra por consola
     * 
     * @param e objeto {@link SQLException} con la información del error
     */
    private void extraerExcepcion(SQLException e) {
        // Obtener el código de error
        String codigo = e.getSQLState();

        // Obtener el mensaje de error
        String mensaje = e.getLocalizedMessage() == null ? e.getMessage() : e.getLocalizedMessage();

        // Mostrar error por consola
        System.err.println(" [!] Código de error SQL: " + codigo);
        System.err.println(" [!] " + mensaje);
    }

    /**
     * Convierte un objeto {@link ResultSet} en un {@link Accidente}
     * 
     * @param resultSet objeto {@link ResultSet} con la información a extraer
     * @return un objeto {@link Accidente}
     */
    private Accidente extraerAccidente(ResultSet resultSet) {
        Accidente accidente = new Accidente();

        try {
            accidente.setId(resultSet.getInt("id"));
            accidente.setFecha(resultSet.getDate("fecha").toLocalDate());
            accidente.setHora(LocalTime.parse(resultSet.getDate("hora").toString()));
            accidente.setDireccion(resultSet.getString("direccion"));
            accidente.setComuna(resultSet.getString("comuna"));
            accidente.setCircunstancia(resultSet.getString("circunstancia"));
            accidente.setLugar(resultSet.getString("lugar"));
            accidente.setDetalles(resultSet.getString("detalles"));
            accidente.setClasificacion(EClasificacion.valueOf(resultSet.getString("clasificacion")));
            accidente.setTipo(ETipo.valueOf(resultSet.getString("tipo")));
            accidente.setMedioPrueba(EPrueba.valueOf(resultSet.getString("medio_prueba")));
        } catch (SQLException e) {
            System.err.println("ERROR: AccidentesRepository#extraerAccidente()");
            extraerExcepcion(e);
        }

        return accidente;
    }

    // CRUD
    // -----------------------------------------------------------------------------------------

    @Override
    public boolean insertarRegistro(Object object) {
        // Conectar con la base de datos
        Connection con = conexion.conectar();

        // Verificar si hubo conexión
        if (con != null) {
            // Armar la consulta
            String sql = "INSERT INTO accidentes (fecha, hora, direccion, comuna, circunstancia,"
                    + " lugar, detalles, clasificacion, tipo, medio_prueba) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                // Convertir el objeto
                Accidente accidente = (Accidente) object;

                // Preparar la consulta
                PreparedStatement statement = con.prepareStatement(sql);

                // Rellenar la consulta con los valores nuevos
                statement.setString(1, accidente.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                statement.setString(2, accidente.getHora().format(DateTimeFormatter.ofPattern("HH:mm")));
                statement.setString(3, accidente.getDireccion());
                statement.setString(4, accidente.getComuna());
                statement.setString(5, accidente.getCircunstancia());
                statement.setString(6, accidente.getLugar());
                statement.setString(7, accidente.getDetalles());
                statement.setString(8, accidente.getClasificacion());
                statement.setString(9, accidente.getTipo());
                statement.setString(10, accidente.getMedioPrueba());

                // Verificar si el registro fue agregado
                boolean fueAgregado = statement.executeUpdate() > 0;

                // Cerrar consulta y conexión
                statement.close();
                conexion.desconectar();

                // Devolver resultado
                return fueAgregado;
            } catch (SQLException e) {
                // Ocurrió un error
                System.err.println("ERROR AccidentesRepository#insertarRegistro()");
                extraerExcepcion(e);
            }
        } else {
            // No hubo conexión con la base de datos
            System.err.println(" [!] No hubo conexión con la base de datos.");
        }

        // Devolver falso si ocurrió algún error durante el proceso de inserción
        return false;
    }

    @Override
    public List<Object> buscarTodos() {
        // Crear lista
        List<Object> accidentes = null;

        // Crear conexión con base de datos
        Connection con = conexion.conectar();

        // Verificar si hubo conexión
        if (con != null) {
            // Preparar consulta
            String sql = "SELECT id, fecha, hora, direccion, comuna, circunstancia, lugar, detalles,"
                    + " clasificacion, tipo, medio_prueba FROM accidentes";

            try {
                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                accidentes = new ArrayList<>();

                // Recoger todos los resultados
                while (rs.next()) {
                    // Crear objeto Accidente
                    Accidente accidente = extraerAccidente(rs);
                    // Agregar objeto al listado
                    accidentes.add(accidente);
                }

                // Cerrar consulta y conexión
                ps.close();
                conexion.desconectar();
            } catch (SQLException e) {
                // Ocurrió un error
                System.err.println("ERROR AccidentesRepository#buscarTodos()");
                extraerExcepcion(e);
            }
        } else {
            // No hubo conexion
            System.err.println(" [!] No hubo conexión con la base de datos. Revise los registros.");
        }

        // Devolver el listado
        return accidentes;
    }

    @Override
    public Object buscarPorID(int id) {
        // Crear el objeto Accidente
        Accidente accidente = null;

        // Conectar con la base de datos
        Connection con = conexion.conectar();

        // Verificar si hubo conexión
        if (con != null) {
            // Armar consulta
            String sql = "SELECT id, fecha, hora, direccion, comuna, circunstancia, lugar, detalles,"
                    + " clasificacion, tipo, medio_prueba FROM accidentes WHERE id = ? LIMIT 1";

            try {
                // Preparar consulta
                PreparedStatement statement = con.prepareStatement(sql);

                // Insertar identificador
                statement.setInt(1, id);

                // Ejecutar consulta
                ResultSet resultado = statement.executeQuery();

                // Verificar si hay resultados
                if (resultado.next()) {
                    // Crear el objeto Accidente
                    accidente = extraerAccidente(resultado);

                    // Cerrar consulta y conexion
                    statement.close();
                    conexion.desconectar();
                } else {
                    // No hay resultados
                    System.err.println(" [!] No existe el registro dentro de la tabla ACCIDENTES con ID = " + id);
                }
            } catch (SQLException e) {
                // Ocurrió un error
                System.err.println("ERROR AccidentesRepository#buscarPorID()");
                extraerExcepcion(e);
            }
        } else {
            // No hubo conexión
            System.err.println(" [!] No hubo conexión con la base de datos.");
        }

        return accidente;
    }

    @Override
    public boolean actualizarRegistro(Object object) {
        // Establecer conexión
        Connection con = conexion.conectar();

        // Verificar si hubo conexión
        if (con != null) {
            // Convertir objeto
            Accidente accidente = (Accidente) object;

            // Buscar accidente en la base de datos
            Accidente aux = (Accidente) this.buscarPorID(accidente.getId());

            // Verificar si existe el accidente
            if (aux != null) {
                // Armar consulta
                String sql = "UPDATE accidentes SET fecha = ?, hora = ?, direccion = ?, comuna = ?"
                        + ", circunstancia = ?, lugar = ?, detalles = ?, clasificacion = ?, "
                        + "tipo = ?, medio_prueba = ? WHERE id = ?";

                try {
                    // Preparar consulta
                    PreparedStatement ps = con.prepareStatement(sql);

                    // Rellenar consulta
                    ps.setDate(1, Date.valueOf(accidente.getFecha()));
                    ps.setDate(2, Date.valueOf(accidente.getHora().format(DateTimeFormatter.ofPattern("HH:mm"))));
                    ps.setString(3, accidente.getDireccion());
                    ps.setString(4, accidente.getComuna());
                    ps.setString(5, accidente.getCircunstancia());
                    ps.setString(6, accidente.getLugar());
                    ps.setString(7, accidente.getDetalles());
                    ps.setString(8, accidente.getClasificacion());
                    ps.setString(9, accidente.getTipo());
                    ps.setString(10, accidente.getMedioPrueba());
                    ps.setInt(11, aux.getId());

                    // Ejecutar consulta
                    boolean seModifico = ps.executeUpdate() > 0;

                    // Cerrar consulta y conexión
                    ps.close();
                    conexion.desconectar();

                    // Devolver resultado obtenido
                    return seModifico;
                } catch (SQLException e) {
                    // Ocurrió un error
                    System.err.println("ERROR AccidentesRepository#actualizarRegistro()");
                    extraerExcepcion(e);
                }
            } else {
                // No existe el registro
                System.err.println(" [!] No existe el registro en la tabla ACCIDENTES con ID = " + accidente.getId());
            }
        } else {
            // No hubo conexión
            System.err.println(" [!] No hubo conexión con la base de datos");
        }

        return false;
    }

    @Override
    public boolean eliminarRegistro(Object object) {
        // Crear conexión
        Connection con = conexion.conectar();

        // Verificar si hay conexión
        if (con != null) {
            // Convertir objeto
            Accidente accidente = (Accidente) object;

            // Buscar registro
            Accidente aux = (Accidente) this.buscarPorID(accidente.getId());

            // Verificar si el registro existe
            if (aux != null) {
                // Armar consulta
                String sql = "DELETE FROM accidentes WHERE id = ?";

                try {
                    // Preparar consulta
                    PreparedStatement ps = con.prepareStatement(sql);

                    // Insertar identificador
                    ps.setInt(1, accidente.getId());

                    // Ejecutar consulta
                    boolean fueEliminado = ps.executeUpdate() > 0;

                    // Cerrar consulta y conexión
                    ps.close();
                    conexion.desconectar();

                    // Devolver valor obtenido
                    return fueEliminado;
                } catch (SQLException e) {
                    // Ocurrió un error
                    System.err.println("ERROR AccidentesRepository#eliminarRegistro()");
                    extraerExcepcion(e);
                }
            } else {
                // El registro no existe
                System.err.println(" [!] No existe el registro en la tabla ACCIDENTES con ID = " + accidente.getId());
            }
        } else {
            // No hubo conexión
            System.err.println(" [!] No hubo conexión con la base de datos");
        }

        return false;
    }

}
