select
    u.`created_at`,
    u.`email`,
    u.`firstname`,
    u.`id`,
    u.`lastname`,
    u.`password`,
    u.`updated_at`
from
    `user` u;

INSERT INTO workouttrackerdb.exercises (name, description, category) VALUES
  ('Push-ups', 'Upper body strength exercise', 'Strength'),
  ('Squats', 'Lower body strength exercise', 'Strength'),
  ('Running', 'Cardio exercise', 'Cardio');

select
    e.`category`,
    e.`description`,
    e.`id`,
    e.`name`,
    e.`workout_plan_id`
from
    `exercises` e;


delete from workouttrackerdb.exercises

