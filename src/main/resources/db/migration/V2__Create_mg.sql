INSERT INTO USER (ID, LOGIN, PASSWORD, SALT) VALUES
  (1, 'Roman', '6b1535f97c2a20f93c899060586b6287', 'e3362c472c18185098f69dedd661cabebfe982e6138863417d2f2672be8976aa'),
  (2, 'Roman1', 'ce53c257bd879ce5b7e0d05fa42c0554', '63f795d6015379214d016f25344ab2ab1f0b1e447c8d1e432f1881dcedf8bf7f'),
  (3, 'Roman2', '9697891b51ca3fea5e969059624dbed1', '3348db9b8b340db30345ffc8ae433a31b0f02ff5f8a49b9591745fd45ffb01a'),
  (4, 'jdoe', '4fd299030ca899b9619e6bec8007059d', '77152917c289c0f17cb8435ff937d9ee7b48ac567f80a6c19d02436fd34f77d9'),
  (5, 'jrow', '30cbb3e82f24021825ab5075857bdef4', '5b4fe4bfd9f57f9d12b5caf7b23b9c466f9728420ea2a1d10dd4aecb8d2a09ee'),
  (6, 'xxx', '5c8f0c4de2fe018dbc4fc2b5d86449f6', 'de38de197ff8995456102131c0d66a8189ad091283b4efb65307db74596e8f28');

INSERT INTO USER_RES (ID, USER_ID, PATH, ROLE) VALUES
  (1, 1, 'a.b', 'READ'),
  (2, 1, 'A.B.C.D', 'READ'),
  (3, 1, 'A.B.C.D', 'WRITE'),
  (4, 2, 'A.B.C.D', 'WRITE'),
  (5, 3, 'a.b', 'EXECUTE'),
  (6, 2, 'AB', 'EXECUTE'),
  (7, 4, 'a', 'READ'),
  (8, 4, 'a.b', 'READ'),
  (9, 4, 'a.bc', 'READ'),
  (10, 5, 'a.b.c', 'EXECUTE');





