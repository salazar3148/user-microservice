INSERT INTO roles VALUES('1', 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO roles VALUES('2', 'ROLE_USER', 'ROLE_USER');
INSERT INTO roles VALUES('3', 'ROLE_OWNER', 'ROLE_OWNER');
INSERT INTO roles VALUES('4', 'ROLE_EMPLOYEE', 'ROLE_EMPLOYEE');

INSERT INTO users (
    id,
    dni_number,
    mail,
    name,
    password,
    phone,
    surname,
    id_role)
VALUES(
          '1',
          '123',
          'email@some.com',
          'Name',
          '$2a$08$iPMEkjWe6x11LPCWMZuuR.XOB0jbY0ghSbPt6Hdz2.hdKIh7RnAt2',
          '1234567890',
          '1'
              'Surname',
          '1');
