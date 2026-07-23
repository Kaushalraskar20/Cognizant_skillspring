import Trainer from './trainer';

/* HOL 6: Mock trainer data — used instead of a backend API */
const TrainersMock = [
  new Trainer(1, 'Alice Johnson', 'alice@cognizant.com', '9876543210', 'React',       ['JavaScript','JSX','Redux']),
  new Trainer(2, 'Bob Smith',     'bob@cognizant.com',   '9876543211', 'Spring Boot', ['Java','REST API','JPA']),
  new Trainer(3, 'Carol White',   'carol@cognizant.com', '9876543212', 'Angular',     ['TypeScript','RxJS','NgRx']),
  new Trainer(4, 'Dave Brown',    'dave@cognizant.com',  '9876543213', 'DevOps',      ['Docker','K8s','Jenkins']),
  new Trainer(5, 'Eve Davis',     'eve@cognizant.com',   '9876543214', 'Python',      ['Django','Flask','ML']),
];

export default TrainersMock;
