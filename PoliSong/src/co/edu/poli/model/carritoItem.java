package co.edu.poli.model;

/**
 * Representa un item dentro del carrito de compras de un usuario
 * en el sistema PoliSongStock. Cada item corresponde a un producto 
 * agregado al carrito (canción, vinilo o MP3) e incluye información 
 * necesaria para realizar un pedido posteriormente.
 * 
 * Proyecto: PoliSongStock
 * Autor: Sebastian Zarate
 */
public class carritoItem {

    /**
     * Identificador único del item dentro del carrito
     */
    private int id_item;

    /**
     * Identificador del carrito al que pertenece este item
     */
    private int id_carrito;

    /**
     * Tipo de producto agregado (cancion, vinilo, discomp3)
     */
    private String tipo_producto;

    /**
     * Identificador del producto relacionado (FK según tipo_producto)
     */
    private int id_porducto;

    /**
     * Cantidad del producto dentro del carrito
     */
    private int cantidad;
    
    /**
     * Constructor vacío requerido para JDBC/DAO
     * útil cuando el item se crea primero y luego se asignan atributos.
     */
    public carritoItem() {
        // Constructor vacío
    }

    /**
     * Constructor que permite crear el objeto indicando solo la FK del carrito.
     * Este constructor es útil cuando se desea construir el objeto para 
     * posteriormente completar la información del item en pasos separados.
     *
     * @param id_carrito ID del carrito al que pertenece este item
     */
    public carritoItem(int id_carrito) {
        super();
        this.id_carrito = id_carrito;
    }

    /**
     * Constructor completo de carritoItem.
     * Permite inicializar todos los atributos del item asociado al carrito.
     *
     * @param id_item Identificador del item
     * @param id_carrito Identificador del carrito
     * @param tipo_producto Tipo de producto (cancion, vinilo, discomp3)
     * @param id_porducto Identificador del producto asociado
     * @param cantidad Cantidad agregada al carrito
     */
    public carritoItem(int id_item, int id_carrito, String tipo_producto, int id_porducto, int cantidad) {
        super();
        this.id_item = id_item;
        this.id_carrito = id_carrito;
        this.tipo_producto = tipo_producto;
        this.id_porducto = id_porducto;
        this.cantidad = cantidad;
    }

    /** @return ID del item */
    public int getId_item() {
        return id_item;
    }

    /** @param id_item Asigna el ID del item */
    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    /** @return ID del carrito al que pertenece este item */
    public int getId_carrito() {
        return id_carrito;
    }

    /** @param id_carrito Asigna el ID del carrito */
    public void setId_carrito(int id_carrito) {
        this.id_carrito = id_carrito;
    }

    /** @return tipo de producto */
    public String getTipo_producto() {
        return tipo_producto;
    }

    /** @param tipo_producto Asigna el tipo de producto (vinilo, cancion, discomp3) */
    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    /** @return ID del producto relacionado */
    public int getId_porducto() {
        return id_porducto;
    }

    /** @param id_porducto Asigna el ID del producto relacionado */
    public void setId_porducto(int id_porducto) {
        this.id_porducto = id_porducto;
    }

    /** @return cantidad del producto en el carrito */
    public int getCantidad() {
        return cantidad;
    }

    /** @param cantidad Asigna la cantidad del producto en el carrito */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
