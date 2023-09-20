import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { Enviroment } from './environments/enviroment';
import { Observable, catchError } from 'rxjs';
import { throwError as observableThrowError } from 'rxjs';
import { Empleado } from './entidad/empleado';

@Injectable({
  providedIn: 'root'
})
export class EmpleadosService {
  Http:HttpClient
  apiUrl:string=Enviroment.api
  constructor(private Https:HttpClient) {
    this.Http=Https;
  }
  errorHandler(error: HttpErrorResponse){
    return observableThrowError(error.message)
  }
  traerEmpleados():Observable<Empleado[]>{
    return this.Http.get<Empleado[]>(`${this.apiUrl}/empleado`).pipe(catchError(this.errorHandler));

  }
  borrarEmpleado(id:number){
    return this.Http.delete(`${this.apiUrl}/empleado/${id}`).pipe(catchError(this.errorHandler))
  }
  obtenerEmpleado(id:number){
    return this.Http.get(`${this.apiUrl}/empleado/${id}`).pipe(catchError(this.errorHandler))
  }
  modificarEmpleado(empleado:Empleado){
    return this.Http.put(`${this.apiUrl}/empleado/${empleado.id}`,empleado).pipe(catchError(this.errorHandler))
    }
  agregarEmpleado(empleado:Empleado){
    return this.Http.post(`${this.apiUrl}/empleado`,empleado).pipe(catchError(this.errorHandler))
  }
}

