import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'tarjeta',
        loadChildren: () => import('./tarjeta/tarjeta.module').then(m => m.ServicioventasjhTarjetaModule)
      },
      {
        path: 'cliente',
        loadChildren: () => import('./cliente/cliente.module').then(m => m.ServicioventasjhClienteModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class ServicioventasjhEntityModule {}
