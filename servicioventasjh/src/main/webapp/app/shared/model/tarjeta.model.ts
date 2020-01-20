import { Moment } from 'moment';
import { ICliente } from 'app/shared/model/cliente.model';

export interface ITarjeta {
  id?: number;
  tarjeta_id?: number;
  cod_seguridad?: number;
  vencimiento?: Moment;
  monto?: number;
  numero?: number;
  tipo?: string;
  cliente?: ICliente;
}

export class Tarjeta implements ITarjeta {
  constructor(
    public id?: number,
    public tarjeta_id?: number,
    public cod_seguridad?: number,
    public vencimiento?: Moment,
    public monto?: number,
    public numero?: number,
    public tipo?: string,
    public cliente?: ICliente
  ) {}
}
