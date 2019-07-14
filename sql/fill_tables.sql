USE `meetups`;

INSERT INTO `user` (
	`login`,
	`password`,
	`permission`
) VALUES (

	"root",
	"1234", /* MD5 хэш пароля "user" */
	1
), (

	"user5",
	"EE11CBB19052E40B07AAC0CA060C23EE", /* MD5 хэш пароля "user" */
	2
), (
	"user6",
	"EE11CBB19052E40B07AAC0CA060C23EE", /* MD5 хэш пароля "user" */
	2
);