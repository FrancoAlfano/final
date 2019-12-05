package ar.edu.um.programacion2.servicioVentas.exception;

public class ClienteNotFoundException extends RuntimeException{


	private static final long serialVersionUID = -5190758696715002960L;

		public ClienteNotFoundException(Long clienteId) {
			super("Cannot find Cliente" + clienteId);
		}

}
