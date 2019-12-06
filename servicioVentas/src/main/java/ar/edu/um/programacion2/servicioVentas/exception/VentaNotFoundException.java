package ar.edu.um.programacion2.servicioVentas.exception;

public class VentaNotFoundException extends RuntimeException{


	private static final long serialVersionUID = -5190758696715002960L;

		public VentaNotFoundException(Long ventaId) {
			super("Cannot find Venta" + ventaId);
		}

}
