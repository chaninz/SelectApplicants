-- ต้องส่งใบสมัคร ถูกให้คะแนน ถึงจะให้ผ่าน/ไม่ผ่านได้
SELECT overviews.wip_id, is_pass, reason, SUM(avg_mark) AS sum_mark 
FROM (SELECT wip_id, question_id, AVG(mark) AS avg_mark FROM marks GROUP BY wip_id, question_id) AS avgmarks 
JOIN overviews ON overviews.wip_id = avgmarks.wip_id 
GROUP BY wip_id ORDER BY is_pass asc, sum_mark desc

SELECT wip_id, question_id, AVG(mark) AS avgmark FROM `marks` GROUP BY wip_id, question_id

SELECT wip_id, marks.question_id, AVG(mark) AS avg_mark FROM marks JOIN questions ON marks.question_id = questions.question_id WHERE wip_id = 50412 GROUP BY wip_id, question_id

-- ให้ผ่านได้โดยไม่ต้องส่งใบสมัครและให้คะแนน กรอกประวัติอย่างเดียวพอ
SELECT overviews.wip_id, is_pass, reason, SUM( avg_mark ) AS sum_mark
FROM (

SELECT wip_id, question_id, AVG( mark ) AS avg_mark
FROM marks
GROUP BY wip_id, question_id
) AS avgmarks
RIGHT OUTER JOIN overviews ON overviews.wip_id = avgmarks.wip_id
GROUP BY wip_id
ORDER BY is_pass ASC , sum_mark DESC 

wip_id
sum_mark
avg_mark

--question
question_id
question

-- overview
is_pass
reason

-- Home เชคว่าแต่ละ user ตรวจใครไปแล้วบ้าง
SELECT * 
FROM (

SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name,  "1" AS 
STATUS 
FROM applicants
JOIN marks ON applicants.wip_id = marks.wip_id
WHERE user_id = 3
GROUP BY user_id, applicants.wip_id
UNION ALL 
SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name,  "2" AS 
STATUS 
FROM applicants
WHERE wip_id NOT 
IN (

SELECT wip_id
FROM marks
WHERE user_id = 3
GROUP BY user_id, wip_id
)
) AS allapp
ORDER BY wip_id


-- เชคว่าคนสมัครแต่ละคน ถูกให้คะแนนครบยัง ประเมินภาพรวมยัง
SELECT applicants.wip_id, marks_status.status AS m_status, marks_status.no_of_checked_question AS m_checked_question, overviews_status.status AS o_status, IFNULL( marks_status.status, 0 ) , IFNULL( overviews_status.status, 0 ) 
FROM applicants
LEFT OUTER JOIN (

SELECT wip_id, COUNT( DISTINCT question_id ) AS no_of_checked_question, 
CASE 
WHEN COUNT( DISTINCT question_id ) = 2 -- นับจำนวน question ที่มีในระบบ จะได้รู้ว่าประเมินกันครบยัง
THEN  "1"
ELSE  "0"
END AS 
STATUS 
FROM marks
GROUP BY wip_id
) AS marks_status ON applicants.wip_id = marks_status.wip_id
LEFT OUTER JOIN (

SELECT wip_id,  "1" AS 
STATUS FROM overviews
) AS overviews_status ON applicants.wip_id = overviews_status.wip_id

-- complete version
SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, IFNULL(overviews_status.status, 0) AS o_status, IFNULL(marks_status.status, 0) AS m_status 
FROM applicants
LEFT OUTER JOIN (SELECT wip_id, COUNT(DISTINCT question_id) AS no_of_checked_question, CASE 
	WHEN COUNT( DISTINCT question_id) = 2
	THEN "1"
	ELSE "0"
	END AS status
	FROM marks
	GROUP BY wip_id) AS marks_status ON applicants.wip_id = marks_status.wip_id
LEFT OUTER JOIN (SELECT wip_id, "1" AS status 
	FROM overviews) AS overviews_status ON applicants.wip_id = overviews_status.wip_id
HAVING m_status = 1 AND o_status = 1

--
SELECT wip_id, COUNT(DISTINCT question_id) AS checked_question, 
CASE
WHEN COUNT(DISTINCT question_id) = 2 THEN "1"
ELSE "0" END AS status
FROM `marks` group by wip_id





SELECT COUNT(*) 
FROM applicants
LEFT OUTER JOIN (SELECT wip_id, COUNT(DISTINCT question_id) AS no_of_checked_question, CASE 
	WHEN COUNT( DISTINCT question_id) = 2
	THEN "1"
	ELSE "0"
	END AS status
	FROM marks
	GROUP BY wip_id) AS marks_status ON applicants.wip_id = marks_status.wip_id
LEFT OUTER JOIN (SELECT wip_id, "1" AS status 
	FROM overviews) AS overviews_status ON applicants.wip_id = overviews_status.wip_id
	
	firstname_th LIKE %keyword% OR lastname_th LIKE %keyword% OR nickname LIKE %keyword% school_name LIKE %keyword% OR