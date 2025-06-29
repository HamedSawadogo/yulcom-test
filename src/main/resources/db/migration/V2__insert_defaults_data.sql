INSERT INTO corporations (id, name, type, createdat, updatedat)
VALUES
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Direction des Douanes', 'PROCESSING_AGENCY', NOW(), NOW()),
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'Chambre de Commerce', 'PROCESSING_AGENCY', NOW(), NOW()),
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Banque partenaire agréée', 'PROCESSING_AGENCY', NOW(), NOW()),
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'Agence nationale d’inspection', 'PROCESSING_AGENCY', NOW(), NOW()),
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Ministère de l’Environnement', 'PROCESSING_AGENCY', NOW(), NOW()),
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17', 'Agence nationale du médicament', 'PROCESSING_AGENCY', NOW(), NOW()),
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Ministère de la Santé', 'PROCESSING_AGENCY', NOW(), NOW()),
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'Trace Import/export', 'EMITTER', NOW(), NOW()),
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a19', 'BCEAO', 'PROCESSING_AGENCY', NOW(), NOW());



INSERT INTO userroles (id, name, description, createdat, updatedat) VALUES
                                                                      ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21', 'FILE_FILLER', 'Can fill files', NOW(), NOW()),
                                                                      ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 'FILE_SIGNER', 'Can sign files', NOW(), NOW()),
                                                                      ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23', 'FILE_VIEWER', 'Can view files', NOW(), NOW()),
                                                                      ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a24', 'FILE_CREATOR', 'Can create folders', NOW(), NOW());

--password: test
INSERT INTO corporateusers (id, corporation_id, username, password, isactive, createdat, lastloginat, updatedat) VALUES
                                                                                                                   ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'hamed.yulcom', '$2a$10$CNAVpQRn0xDZZBfvVDCIxu3JBPCze6IFBjbXVnr1/WIfWuLRyqsKC', TRUE, NOW(), NOW(), NOW()),
                                                                                                                   ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'hamed.agitex', '$2a$10$CNAVpQRn0xDZZBfvVDCIxu3JBPCze6IFBjbXVnr1/WIfWuLRyqsKC', TRUE, NOW(), NOW(), NOW());


INSERT INTO corporateusers_userroles (corporateuser_id, roles_id) VALUES
  ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a01', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a24');

INSERT INTO corporateusers_userroles (corporateuser_id, roles_id) VALUES
                                                                    ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21'),
                                                                    ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');

