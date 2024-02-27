type TipoUsuario = 'user' | 'admin';

export type User = {
  id: number;
  nombre: string;
  apellidos: string;
  email: string;
  imagen: string;
  password: string;
  tipo: TipoUsuario;
};
