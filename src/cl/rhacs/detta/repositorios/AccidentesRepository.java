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

import cl.rhacs.detta.Utilidades;
import cl.rhacs.detta.modelos.Accidente;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.interfaces.IAccidentesRepository;

public class AccidentesRepository implements IAccidentesRepository {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Nombre de la tabla en la base de datos */
    private final String TABLA = "detta_accidentes";

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link Conexion} con los métodos de conexión a la base de datos */
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

    /**
     * Extrae un {@link Accidente} de un {@link ResultSet}
     * 
     * @param rs objeto {@link ResultSet} con la información a extraer
     * @return un objeto {@link Accidente}
     */
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
            accidente.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
            accidente.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion").toLocalDateTime());
            accidente.setEmpresaId(rs.getInt("empresa_id"));
        } catch (SQLException e) {
            Utilidades.extraerError("AccidentesRepository", "extraerAccidente", e);
        }

        return accidente;
    }

    // CRUD
    // -----------------------------------------------------------------------------------------

    @Override
    public boolean agregarRegistro(Object objeto) {
        // Crear respuesta
        boolean registroAgregado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "INSERT INTO " + TABLA + " (fecha, hora, direccion, lugar, circunstancia, "
                        + "detalles, clasificacion, tipo, medio_prueba, fecha_registro, fecha_actualizacion, "
                        + "empresa_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";

                // Convertir objeto
                Accidente accidente = (Accidente) objeto;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setDate(1, Date.valueOf(accidente.getFecha()));
                ps.setString(2, accidente.getHora().format(DateTimeFormatter.ofPattern("HH:mm")));
                ps.setString(3, accidente.getDireccion());
                ps.setString(4, accidente.getLugar());
                ps.setString(5, accidente.getCircunstancia());
                ps.setString(6, accidente.getDetalles());
                ps.setString(7, accidente.getClasificacion());
                ps.setString(8, accidente.getTipo());
                ps.setString(9, accidente.getMedioPrueba());
                ps.setInt(10, accidente.getEmpresaId());

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

        // Verificar conexion
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, hora, direccion, lugar, circunstancia, detalles, clasificacion, "
                        + "tipo, medio_prueba, fecha_registro, fecha_actualizacion, empresa_id FROM " + TABLA
                        + " ORDER BY fecha DESC";

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

                        // Agregar al listado
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

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, hora, direccion, lugar, circunstancia, detalles, clasificacion, "
                        + "tipo, medio_prueba, fecha_registro, fecha_actualizacion, empresa_id FROM " + TABLA
                        + " WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultado
                if (rs.next()) {
                    // Extraer resultado
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
    public List<Accidente> buscarPorEmpresaId(int empresaId) {
        // Crear objeto
        List<Accidente> accidentes = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, hora, direccion, lugar, circunstancia, detalles, clasificacion, "
                        + "tipo, medio_prueba, fecha_registro, fecha_actualizacion, empresa_id FROM " + TABLA
                        + " WHERE empresa_id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, empresaId);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultado
                if (rs.next()) {
                    // Inicializar listado
                    accidentes = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Accidente accidente = extraerAccidente(rs);

                        // Agregar a listado
                        accidentes.add(accidente);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError("AccidentesRepository", "buscarPorEmpresaId", e);
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

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "UPDATE " + TABLA + " SET fecha = ?, hora = ?, direccion = ?, lugar = ?, "
                        + "circunstancia = ?, detalles = ?, clasificacion = ?, tipo = ?, medio_prueba = ?, "
                        + "fecha_actualizacion = CURRENT_TIMESTAMP, empresa_id = ? WHERE id = ?";

                // Convertir objeto
                Accidente accidente = (Accidente) objeto;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setDate(1, Date.valueOf(accidente.getFecha()));
                ps.setString(2, accidente.getHora().format(DateTimeFormatter.ofPattern("HH:mm")));
                ps.setString(3, accidente.getDireccion());
                ps.setString(4, accidente.getLugar());
                ps.setString(5, accidente.getCircunstancia());
                ps.setString(6, accidente.getDetalles());
                ps.setString(7, accidente.getClasificacion());
                ps.setString(8, accidente.getTipo());
                ps.setString(9, accidente.getMedioPrueba());
                ps.setInt(10, accidente.getEmpresaId());
                ps.setInt(11, accidente.getId());

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
                String sql = "DELETE FROM " + TABLA + " WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
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
