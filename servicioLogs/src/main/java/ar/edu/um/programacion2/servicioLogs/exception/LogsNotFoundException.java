package ar.edu.um.programacion2.servicioLogs.exception;

public class LogsNotFoundException extends RuntimeException{


	private static final long serialVersionUID = -5190758696715002960L;

		public LogsNotFoundException(Long logsId) {
			super("Cannot find Logs " + logsId);
		}

}
