package ar.edu.um.programacion2.servicioTarjeta.exception;

public class TarjetaNotFoundException extends RuntimeException{


	private static final long serialVersionUID = -5190758696715002960L;

		public TarjetaNotFoundException(Long tarjetaId) {
			super("Cannot find Tarjeta " + tarjetaId);
		}

}
