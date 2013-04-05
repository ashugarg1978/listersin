<?php
/**
 * The base configurations of the WordPress.
 *
 * This file has the following configurations: MySQL settings, Table Prefix,
 * Secret Keys, WordPress Language, and ABSPATH. You can find more information
 * by visiting {@link http://codex.wordpress.org/Editing_wp-config.php Editing
 * wp-config.php} Codex page. You can get the MySQL settings from your web host.
 *
 * This file is used by the wp-config.php creation script during the
 * installation. You don't have to use the web site, you can just copy this file
 * to "wp-config.php" and fill in the values.
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define('DB_NAME', 'listersin_wordpress');

/** MySQL database username */
define('DB_USER', 'listersin');

/** MySQL database password */
define('DB_PASSWORD', 'lzF2p4');

/** MySQL hostname */
define('DB_HOST', '10.156.17.98');

/** Database Charset to use in creating database tables. */
define('DB_CHARSET', 'utf8');

/** The Database Collate type. Don't change this if in doubt. */
define('DB_COLLATE', '');

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define('AUTH_KEY',         'x1?T@96tX`9oGg%eot}*B7<;B2km(X0?0`[4njc30F@[DGPs$R:<GJ{p1y*VmSo-');
define('SECURE_AUTH_KEY',  '<o>:-Ig:^z__G@-e)u|f1A@&?k9|y?XxX2,QfC)2mK$kuP|_h;v_@Z$<xTMI!~WK');
define('LOGGED_IN_KEY',    '&ycEv,eddHG<.`VK@[,_]/E+X()G]rucEY{WPHdEnfMT u55BwzkQqcY7vLMtJp7');
define('NONCE_KEY',        'vey-R^%3g+,LgJg]Bz<Os0=<8u.(e]EN>2:XKpm7y_[PbBjJ-yH+_Bh>,+^<Oy@&');
define('AUTH_SALT',        '0*pg]i,Y t4w5usI<WjN|SFIYgt/&-9_^t$qAKQpZ;|!qPdIj^I/QEY.xkTIcl|v');
define('SECURE_AUTH_SALT', 'r*-3qc_.)gcdQe,h*QFGcS5qN:K+`:v**VrDRP/?^Vzw-*R-M)NK;h/O=AW4-HIv');
define('LOGGED_IN_SALT',   '][fk+?rcpEgBs9O%ix%A}}G$-A:$W%(!h)~3;ZF>`p;m1||gI9+{mjq0!J*9obnp');
define('NONCE_SALT',       'KS^dzJvi}]MM68b^X2HU*Yh1FuMh9P|S`>&7 lh<*Z,YL+|w_F)|+9:;iAN/AUdr');

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each a unique
 * prefix. Only numbers, letters, and underscores please!
 */
$table_prefix  = 'wp_';

/**
 * WordPress Localized Language, defaults to English.
 *
 * Change this to localize WordPress. A corresponding MO file for the chosen
 * language must be installed to wp-content/languages. For example, install
 * de_DE.mo to wp-content/languages and set WPLANG to 'de_DE' to enable German
 * language support.
 */
define('WPLANG', '');

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 */
define('WP_DEBUG', false);

/* That's all, stop editing! Happy blogging. */

/** Absolute path to the WordPress directory. */
if ( !defined('ABSPATH') )
	define('ABSPATH', dirname(__FILE__) . '/');

/** Sets up WordPress vars and included files. */
require_once(ABSPATH . 'wp-settings.php');
