import { ITarjeta } from 'app/shared/model/tarjeta.model';

export interface ICliente {
  id?: number;
  cliente_id?: number;
  nombre?: string;
  apellido?: string;
  clientes?: ITarjeta[];
}

export class Cliente implements ICliente {
  constructor(
    public id?: number,
    public cliente_id?: number,
    public nombre?: string,
    public apellido?: string,
    public clientes?: ITarjeta[]
  ) {}
}
