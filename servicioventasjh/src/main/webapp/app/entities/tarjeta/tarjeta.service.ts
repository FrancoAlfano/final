import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITarjeta } from 'app/shared/model/tarjeta.model';

type EntityResponseType = HttpResponse<ITarjeta>;
type EntityArrayResponseType = HttpResponse<ITarjeta[]>;

@Injectable({ providedIn: 'root' })
export class TarjetaService {
  public resourceUrl = SERVER_API_URL + 'api/tarjetas';

  constructor(protected http: HttpClient) {}

  create(tarjeta: ITarjeta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tarjeta);
    return this.http
      .post<ITarjeta>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tarjeta: ITarjeta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tarjeta);
    return this.http
      .put<ITarjeta>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITarjeta>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITarjeta[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tarjeta: ITarjeta): ITarjeta {
    const copy: ITarjeta = Object.assign({}, tarjeta, {
      vencimiento: tarjeta.vencimiento != null && tarjeta.vencimiento.isValid() ? tarjeta.vencimiento.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.vencimiento = res.body.vencimiento != null ? moment(res.body.vencimiento) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tarjeta: ITarjeta) => {
        tarjeta.vencimiento = tarjeta.vencimiento != null ? moment(tarjeta.vencimiento) : null;
      });
    }
    return res;
  }
}
