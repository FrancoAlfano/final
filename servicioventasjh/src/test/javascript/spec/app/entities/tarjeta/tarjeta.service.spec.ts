import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { TarjetaService } from 'app/entities/tarjeta/tarjeta.service';
import { ITarjeta, Tarjeta } from 'app/shared/model/tarjeta.model';

describe('Service Tests', () => {
  describe('Tarjeta Service', () => {
    let injector: TestBed;
    let service: TarjetaService;
    let httpMock: HttpTestingController;
    let elemDefault: ITarjeta;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(TarjetaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Tarjeta(0, 0, 0, currentDate, 0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            vencimiento: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Tarjeta', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            vencimiento: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            vencimiento: currentDate
          },
          returnedFromService
        );
        service
          .create(new Tarjeta(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Tarjeta', () => {
        const returnedFromService = Object.assign(
          {
            tarjeta_id: 1,
            cod_seguridad: 1,
            vencimiento: currentDate.format(DATE_FORMAT),
            monto: 1,
            numero: 1,
            tipo: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            vencimiento: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Tarjeta', () => {
        const returnedFromService = Object.assign(
          {
            tarjeta_id: 1,
            cod_seguridad: 1,
            vencimiento: currentDate.format(DATE_FORMAT),
            monto: 1,
            numero: 1,
            tipo: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            vencimiento: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Tarjeta', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
