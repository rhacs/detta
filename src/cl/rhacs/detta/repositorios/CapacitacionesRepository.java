package cl.rhacs.detta.repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.rhacs.detta.Utilidades;
import cl.rhacs.detta.modelos.Capacitacion;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.interfaces.ICapacitacionesRepository;

public class CapacitacionesRepository implements ICapacitacionesRepository {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Nombre de la tabla en base de datos */
    private final String TABLA = "detta_capacitaciones";

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link Conexion} con los métodos de conexión a base de datos */
    private Conexion conexion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    public CapacitacionesRepository(Conexion conexion) {
        this.conexion = conexion;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    private Capacitacion extraerCapacitacion(ResultSet rs) {
        // Crear objeto
        Capacitacion capacitacion = new Capacitacion();

        try {
            // Extraer información
            capacitacion.setId(rs.getInt("id"));
            capacitacion.setFecha(rs.getDate("fecha").toLocalDate());
            capacitacion.setDireccion(rs.getString("direccion"));
            capacitacion.setTema(rs.getString("tema"));
            capacitacion.setParticipantes(rs.getInt("participantes"));
            capacitacion.setRealizada(rs.getBoolean("realizada"));
            capacitacion.setProfesionalId(rs.getInt("profesional_id"));
            capacitacion.setEmpresaId(rs.getInt("empresa_id"));
        } catch (SQLException e) {
            Utilidades.extraerError("CapacitacionesRepository", "extraerCapacitacion", e);
        }

        return capacitacion;
    }

    // CRUD
    // -----------------------------------------------------------------------------------------

    @Override
    public boolean agregarRegistro(Object objeto) {
        // Convertir objeto
        Capacitacion capacitacion = (Capacitacion) objeto;

        // Crear respuesta
        boolean registroAgregado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "INSERT INTO " + TABLA + " (fecha, direccion, tema, participantes, realizada, "
                        + "profesional_id, empresa_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setDate(1, Date.valueOf(capacitacion.getFecha()));
                ps.setString(2, capacitacion.getDireccion());
                ps.setString(3, capacitacion.getTema());
                ps.setInt(4, capacitacion.getParticipantes());
                ps.setBoolean(5, capacitacion.isRealizada());
                ps.setInt(6, capacitacion.getProfesionalId());
                ps.setInt(7, capacitacion.getEmpresaId());

                // Ejecutar consulta
                registroAgregado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError("CapacitacionesRepository", "agregarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroAgregado;
    }

    @Override
    public List<Object> buscarTodos() {
        // Crear respuesta
        List<Object> capacitaciones = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, direccion, tema, participantes, realizada, profesional_id, "
                        + "empresa_id FROM " + TABLA + " ORDER BY fecha DESC";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar resultados
                if (rs.next()) {
                    // Inicializar listado
                    capacitaciones = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Capacitacion capacitacion = extraerCapacitacion(rs);

                        // Agregar listado
                        capacitaciones.add(capacitacion);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError("CapacitacionesRepository", "buscarTodos", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return capacitaciones;
    }

    @Override
    public Object buscarPorId(int id) {
        // Crear respuesta
        Capacitacion capacitacion = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexion
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, direccion, tema, participaciones, realizada, profesional_id, "
                        + "empresa_id FROM " + TABLA + " WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar resultados
                if (rs.next()) {
                    // Extraer resultado
                    capacitacion = extraerCapacitacion(rs);
                }
            } catch (SQLException e) {
                Utilidades.extraerError("CapacitacionesRepository", "buscarPorId", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return capacitacion;
    }

    @Override
    public List<Capacitacion> buscarPorProfesionalId(int profesionalId) {
        // Crear respuesta
        List<Capacitacion> capacitaciones = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexion
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, direccion, tema, participantes, realizada, profesional_id, "
                        + " empresa_id FROM " + TABLA + " WHERE profesional_id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, profesionalId);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar resultados
                if (rs.next()) {
                    // Inicializar listado
                    capacitaciones = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Capacitacion capacitacion = extraerCapacitacion(rs);

                        // Agregar al listado
                        capacitaciones.add(capacitacion);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError("CapacitacionesRepository", "buscarPorProfesionalId", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return capacitaciones;
    }

    @Override
    public List<Capacitacion> buscarPorEmpresaId(int empresaId) {
        // Crear respuesta
        List<Capacitacion> capacitaciones = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexion
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, fecha, direccion, tema, participantes, realizada, profesional_id, "
                        + " empresa_id FROM " + TABLA + " WHERE empresa_id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, empresaId);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar resultados
                if (rs.next()) {
                    // Inicializar listado
                    capacitaciones = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Capacitacion capacitacion = extraerCapacitacion(rs);

                        // Agregar al listado
                        capacitaciones.add(capacitacion);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError("CapacitacionesRepository", "buscarPorEmpresaId", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return capacitaciones;
    }

    @Override
    public boolean actualizarRegistro(Object objeto) {
        // Crear respuesta
        boolean registroActualizado = false;

        // Convertir objeto
        Capacitacion capacitacion = (Capacitacion) objeto;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "UPDATE " + TABLA + " SET fecha = ?, direccion = ?, tema = ?, participantes = ?, "
                        + "realizada = ?, profesional_id = ?, empresa_id = ? WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setDate(1, Date.valueOf(capacitacion.getFecha()));
                ps.setString(2, capacitacion.getDireccion());
                ps.setString(3, capacitacion.getTema());
                ps.setInt(4, capacitacion.getParticipantes());
                ps.setBoolean(5, capacitacion.isRealizada());
                ps.setInt(6, capacitacion.getProfesionalId());
                ps.setInt(7, capacitacion.getEmpresaId());
                ps.setInt(8, capacitacion.getId());

                // Ejecutar consulta
                registroActualizado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError("CapacitacionesRepository", "actualizarRegistro", e);
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
                Utilidades.extraerError("CapacitacionesRepository", "eliminarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroEliminado;
    }

}
