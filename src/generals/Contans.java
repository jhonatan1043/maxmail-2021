/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generals;

/**
 *
 * @author Programador 1
 */
public class Contans {

    public static int ID_MENSAJE_NORMAL = 0;
    public static int ID_MENSAJE_ERROR = 1;

    public static int MAXIMO_LINK = 4500;

    public static String SELECTING = "-- Seleccionar --";

    public static String CORREO_REMITENTE = "Correo Remitente: ";

    public static String CORREO_PROCESADOS = "Correos Procesados: ";

    public static String CORREO_ADMIN_ENVIO = "adm.zeusblack@gmail.com";

    public static String CORREO_ADMIN_RECIBE = "remitente.zeusblack@gmail.com";

    public static String CORREO_PASS_DEFAULT = "Eduardo94.";

    public static String CORREO_SERVIDOR_DEFAULT = "smtp.gmail.com";

    public static int CORREO_PORT_DEFAULT = 587;

    //------------------------------------------------------------------------------------------
    public static String QUERY_PLANTILLA = "SELECT  id, nombre FROM Plantillas";

    public static String QUERY_GET_PLANTILLA = "SELECT plantilla FROM Plantillas WHERE id = ?";

    public static String QUERY_GET_PLANTILLA_VALUE = "SELECT Id FROM Plantillas WHERE nombre = ?";

    public static String QUERY_PLANTILLA_BUSQUEDA = "SELECT Id, nombre FROM Plantillas WHERE nombre LIKE '%";

    public static String QUERYS_PLANTILLA_INSERT = "INSERT INTO Plantillas(nombre, plantilla)"
            + "VALUES(?,?);";

    public static String QUERYS_PLANTILLA_UPDATE = "UPDATE Plantillas SET nombre = ?, "
            + "plantilla= ? "
            + "WHERE id = ?; ";

    public static String QUERYS_PLANTILLA_DELETE = "DELETE FROM Plantillas WHERE id = ?";

    public static String QUERY_GET_REGISTRO_PLANTILLA = "SELECT nombre, plantilla"
            + " FROM Plantillas WHERE id = ? ";

    //-------------------------------------------------------------------------------------------------------------
    public static String QUERY_SERVIDOR = "SELECT id, nombre FROM Servidor";

    public static String QUERY_GET_SERVIDOR_VALUE = "SELECT Id FROM Servidor WHERE nombre = ?";

    public static String QUERYS_SERVIDOR_DELETE = "DELETE FROM Servidor WHERE id = ?";

    public static String QUERYS_SERVIDOR_INSERT = "INSERT INTO Servidor(nombre, servidor, puerto)"
            + "VALUES(?,?,?);";

    public static String QUERYS_SERVIDOR_UPDATE = "UPDATE Servidor SET nombre = ?, "
            + " servidor = ?,"
            + " puerto = ? "
            + "WHERE id = ?; ";

    public static String QUERY_GET_REGISTRO_SERVIDOR = "SELECT nombre, servidor, puerto"
            + " FROM Servidor WHERE id = ? ";

    public static String QUERY_SERVIDOR_BUSQUEDA = "SELECT Id, nombre FROM Servidor WHERE nombre LIKE '%";

    //--------------------------------------------------------------------------------------------------------------
    public static String QUERY_PARAMETRO = "SELECT nombre FROM parametros";

    public static String QUERY_PARAMETRO_USUARIO = "SELECT nombre FROM Parametros "
            + " JOIN Usuario_Detalle "
            + " ON Parametros.id = Usuario_Detalle.idParametro"
            + " WHERE Usuario_Detalle.idUsuario = ? AND Usuario_Detalle.idPlantilla = ? AND numMinEmail > 0 Order by id";

    public static String QUERY_PARAMETRO_BUSQUEDA = "SELECT Id, nombre, remitente FROM parametros WHERE nombre LIKE '%";

    public static String QUERYS_PARAMETRO_INSERT = "INSERT INTO parametros(nombre, remitente, password, idservidor, auth, seguridad, numEmail, numMinEmail, nombrePerfil)"
            + "VALUES(?,?,?,?,?,?,?,?,?);";

    public static String QUERYS_PARAMETRO_UPDATE = "UPDATE parametros SET nombre = ?, "
            + "remitente= ?, "
            + "password = ?, "
            + "idservidor = ? , "
            + "auth = ?,"
            + "seguridad= ?,"
            + "numEmail = ?, numMinEmail = ?, nombrePerfil= ?  WHERE id = ?; ";

    public static String QUERYS_PARAMETRO_DELETE = "DELETE FROM Parametros WHERE id = ?";

    public static String QUERY_GET_PARAMETRO = "SELECT remitente, "
            + " password, servidor.servidor, servidor.puerto, numEmail, numMinEmail,auth, seguridad, parametros.nombre "
            + " FROM parametros JOIN Servidor "
            + " ON parametros.idservidor = servidor.id WHERE parametros.id = ?";

    public static String QUERY_GET_REGISTRO_PARAMETRO = "SELECT nombre, remitente, password, idservidor, auth, seguridad, numEmail, nombrePerfil"
            + " FROM parametros WHERE id = ? ";

    public static String QUERY_GET_PARAMETRO_VALUE = "SELECT Id FROM parametros WHERE nombre = ?";

    public static String QUERY_DISMINUIR_CANTIDAD = "UPDATE parametros SET numMinEmail = numMinEmail - 1 WHERE id = ?";

    //---------------------------------------------------------------------------------------------------------------------------
    public static String QUERY_GET_PC = "SELECT Id FROM Seguridad WHERE pc = ?";

    public static String QUERY_INSERT_SEGURIDAD = "INSERT INTO Seguridad(pc, llave)VALUES (?,?);";

    public static String QUERY_UPDATE_ACTIVO = "UPDATE Seguridad SET activo = 1 WHERE llave = ? and pc = ?;";

    public static String QUERY_GET_VERIFICACION = "SELECT id FROM Seguridad WHERE pc = ? AND activo = 1";

    //----------------------------------------------------------------------------------------------------------------------------
    public static String QUERY_GET_USUARIO = "SELECT Id, permiso, fechaActividad FROM Usuarios WHERE usuario = ? AND password = ?";
    //------------------------------------------------------------------------------------------
    public static String QUERY_USUARIO = "SELECT  id, usuario FROM Usuarios";

    public static String QUERY_LOAD_USUARIO = "SELECT usuario FROM Usuarios WHERE id = ?";

    public static String QUERY_GET_USUARIO_VALUE = "SELECT Id FROM Usuarios WHERE usuario = ?";

    public static String QUERY_USUARIO_BUSQUEDA = "SELECT Id, usuario FROM Usuarios WHERE usuario LIKE '%";

    public static String QUERYS_USUARIO_INSERT = "INSERT INTO Usuarios(usuario,password,permiso)"
            + "VALUES(?,?,?);";

    public static String QUERYS_USUARIO_UPDATE = "UPDATE Usuarios SET usuario = ?, "
            + "password= ?, "
            + "permiso= ? "
            + "WHERE id = ?; ";

    public static String QUERYS_USUARIO_DELETE = "DELETE FROM Usuarios WHERE id = ?";

    public static String QUERY_GET_REGISTRO_USUARIO = "SELECT Usuario,password,permiso"
            + " FROM Usuarios WHERE id = ? ";

    public static String QUERY_RESTAURAR_CONTEO = "CALL spRestaurarConteo(?);";
    //---------------------------------------------------------------------------------------------------------------------------------
    public static String QUERY_LOAD_USUARIO_CORREO = "SELECT Usuario_Detalle.idParametro, Parametros.nombre, Parametros.remitente "
            + "FROM Usuario_Detalle "
            + "JOIN Parametros ON Usuario_Detalle.idParametro =  Parametros.id "
            + "WHERE Usuario_Detalle.idUsuario = ? AND Usuario_Detalle.idPlantilla = ? Order by Parametros.id";

    public static String QUERY_USUARIO_PARAMETRO_BUSQUEDA = "SELECT Id, nombre, remitente FROM parametros "
            + "WHERE NOT EXISTS (SELECT 1 FROM Usuario_Detalle WHERE Usuario_Detalle.idParametro = parametros.id ) "
            + "AND nombre LIKE '%";

    public static String QUERY_LOAD_PARAMETRO = "SELECT nombre, remitente FROM parametros WHERE id = ?";

    public static String QUERY_INSERT_USUARIO_CORREO = "INSERT INTO Usuario_Detalle(idUsuario, idPlantilla, idParametro)VALUES(?,?,?);";

    public static String QUERY_DELETE_USUARIO_CORREO = "DELETE FROM Usuario_Detalle WHERE idUsuario = ? AND idPlantilla = ?;";
    //------------------------------------------------------------------------------------------------------------------------------------
    public static String QUERY_CAPTURE = "SELECT ipTem, correoTem ,passTem ,pinTem ,created_at FROM tabla_sistema_tem ORDER BY created_at";
}
