package cl.rhacs.detta.repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import cl.rhacs.detta.Utilidades;
import cl.rhacs.detta.modelos.Accidente;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.interfaces.IAccidentesRepository;

public class AccidentesRepository implements IAccidentesRepository {

    // Atributos
    // -----------------------------------------------------------------------------------------

    private Conexion conexion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link AccidentesRepository}
     * 
     * @param conexion objeto {@link Conexion} con la conexión a la base de datos
     */
    public AccidentesRepository(Conexion conexion) {
        this.conexion = conexion;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    private Accidente extraerAccidente(ResultSet rs) {
        // Crear respuesta
        Accidente accidente = new Accidente();

        try {
            // Llenar accidente
            accidente.setId(rs.getInt("id"));
            accidente.setFecha(rs.getDate("fecha").toLocalDate());
            accidente.setHora(LocalTime.parse(rs.getString("hora")));
            accidente.setDireccion(rs.getString("direccion"));
            accidente.setLugar(rs.getString("lugar"));
            accidente.setCircunstancia(rs.getString("circunstancia"));
            accidente.setDetalles(rs.getString("detalles"));
            accidente.setClasificacion(rs.getString("clasificacion"));
            accidente.setTipo(rs.getString("tipo"));
            accidente.setMedioPrueba(rs.getString("medio_prueba"));
            accidente.setRegistro(rs.getTimestamp("registro").toLocalDateTime());
            accidente.setActualizacion(rs.getTimestamp("actualizacion").toLocalDateTime());
        } catch (SQLException e) {
            Utilidades.extraerError("AccidentesRepository", "extraerAccidente", e);
        }

        return accidente;
    }

    // CRUD
    // -----------------------------------------------------------------------------------------

    @Override
    public boolean agregarRegistro(Object objeto, int padreId) {
        // Crear respuesta
        boolean registroAgregado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "INSERT INTO accidentes (fecha, hora, direccion, lugar, circunstancia"
                        + ", detalles, clasificacion, tipo, medio_prueba, registro, actualizacion"
                        + "empresa_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, "
                        + "CURRENT_TIMESTAMP, ?)";

                // Convertir objeto
                Accidente accidente = (Accidente) objeto;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setDate(1, Date.valueOf(accidente.getFecha()));
                ps.setString(2, accidente.getHora().format(DateTimeFormatter.ofPattern("HH:mm")));
                ps.setString(3, accidente.getDireccion());
                ps.setString(4, accidente.getLugar());
                ps.setString(5, accidente.getCircunstancia());
                ps.setString(6, accidente.getDetalles());
                ps.setString(7, accidente.getClasificacion());
                ps.setString(8, accidente.getTipo());
                ps.setString(9, accidente.getMedioPrueba());
                ps.setInt(10, padreId);

                // Ejecutar consulta
                registroAgregado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError("AccidentesRepository", "agregarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroAgregado;
    }

    @Override
    public List<Object> buscarTodos() {
        // Crear listado
        List<Object> accidentes = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, hora, direccion, lugar, circunstancia, detalles, "
                        + "clasificacion, tipo, medio_prueba, registro, actualizacion FROM "
                        + "accidentes ORDER BY actualizacion DESC";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar listado
                    accidentes = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Accidente accidente = extraerAccidente(rs);

                        // Agregar accidente al listado
                        accidentes.add(accidente);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError("AccidentesRepository", "buscarTodos", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return accidentes;
    }

    @Override
    public Object buscarPorId(int id) {
        // Crear objeto
        Accidente accidente = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar si hay conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, hora, direccion, lugar, circunstancia, detalles, "
                        + "clasificacion, tipo, medio_prueba, registro, actualizacion FROM "
                        + "accidentes WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Extraer accidente
                    accidente = extraerAccidente(rs);
                }
            } catch (SQLException e) {
                Utilidades.extraerError("AccidentesRepository", "buscarPorId", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return accidente;
    }

    @Override
    public List<Accidente> buscarPorFecha(LocalDate fecha) {
        // Crear listado
        List<Accidente> accidentes = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, hora, direccion, lugar, circunstancia, detalles, "
                        + "clasificacion, tipo, medio_prueba, registro, actualizacion FROM "
                        + "accidentes WHERE fecha = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setDate(1, Date.valueOf(fecha));

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar listado
                    accidentes = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Accidente accidente = extraerAccidente(rs);

                        // Agregar accidente al listado
                        accidentes.add(accidente);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError("AccidentesRepository", "buscarPorFecha", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return accidentes;
    }

    @Override
    public List<Accidente> buscarPor(String campo, String valor) {
        // Crear listado
        List<Accidente> accidentes = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexion
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, hora, direccion, lugar, circunstancia, detalles, "
                        + "clasificacion, tipo, medio_prueba, registro, actualizacion FROM accidentes WHERE ? = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setString(1, campo);
                ps.setString(2, valor);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar listado
                    accidentes = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Accidente accidente = extraerAccidente(rs);

                        // Insertar accidente en el listado
                        accidentes.add(accidente);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError("AccidentesRepository", "buscarPor", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return accidentes;
    }

    @Override
    public boolean actualizarRegistro(Object objeto) {
        // Crear respuesta
        boolean registroActualizado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar si hay conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "UPDATE accidentes SET fecha = ?, hora = ?, direccion = ?, lugar = ?, "
                        + "circunstancia = ?, detalles = ?, clasificacion = ?, tipo = ? "
                        + "medio_prueba = ?, actualizacion = CURRENT_TIMESTAMP WHERE id = ?";

                // Convertir objeto
                Accidente accidente = (Accidente) objeto;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setDate(1, Date.valueOf(accidente.getFecha()));
                ps.setString(2, accidente.getHora().format(DateTimeFormatter.ofPattern("HH:mm")));
                ps.setString(3, accidente.getDireccion());
                ps.setString(4, accidente.getLugar());
                ps.setString(5, accidente.getCircunstancia());
                ps.setString(6, accidente.getDetalles());
                ps.setString(7, accidente.getClasificacion());
                ps.setString(8, accidente.getTipo());
                ps.setString(9, accidente.getMedioPrueba());
                ps.setInt(10, accidente.getId());

                // Ejecutar consulta
                registroActualizado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError("AccidentesRepository", "actualizarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroActualizado;
    }

    @Override
    public boolean eliminarRegistro(int id) {
        // Crear respuesta
        boolean registroEliminado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "DELETE FROM accidentes WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                registroEliminado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError("AccidentesRepository", "eliminarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroEliminado;
    }

}
