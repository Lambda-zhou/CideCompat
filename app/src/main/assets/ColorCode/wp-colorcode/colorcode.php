<?php
/*
Plugin Name: ColorCode Highlight
Plugin URI: http://www.pizn.net
Description: Javascript highlight code's color sulotions.
Version: Version 1.0
Author:	PIZn
Author URI: http://www.pizn.net
Liscense: A "Slug" license name e.g. GPL2
*/
?>
<?php
/*  Copyright YEAR  PLUGIN_AUTHOR_NAME  (email : pizner@gmail.com)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License, version 2, as 
    published by the Free Software Foundation.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
?>
<?php
function wp_colorcode_style(){
	$wp_colorcode_themes=get_option('wp_colorcode_themes');
	if($wp_colorcode_themes=='random'){
		srand((float) microtime() *10000000);
		$theme_style[1]='cc-knight';
		$theme_style[2]='cc-moonfang';
		$theme_style[3]='cc-nortrom';
		$theme_style[4]='cc-yurnero';
		$rand_theme=array_rand($theme_style);
		$wp_colorcode_themes=$theme_style[$rand_theme];
	}
	$wp_colorcode_css_url = plugins_url('/css/' . $wp_colorcode_themes . '.css?ver=20111206', __FILE__);
	if(file_exists(TEMPLATEPATH . "/cc-knight.css?ver=20111206")){
		$wp_colorcode_css_url = get_bloginfo("template_url") . "/cc-knight.css?ver=20111206";
	}
	if(get_option('wp_colorcode_themes')!=''){
		echo '<link rel="stylesheet" type="text/css" href="'.$wp_colorcode_css_url.'" media="screen" />' . "\n";
	}
	else{
		$wp_colorcode_css_url = plugins_url('/css/cc-knight.css?ver=20111206', __FILE__);
		echo '<link rel="stylesheet" type="text/css" href="'.$wp_colorcode_css_url.'" media="screen" />' . "\n";
	}
}
add_action("wp_head",'wp_colorcode_style');
function wp_colorcode_js(){
	$wp_colorcode_line_number = get_option('wp_colorcode_line_number');
	if($wp_colorcode_line_number == 'enable' ) {
		$wp_colorcode_line_number = 'true';
	} else {
		$wp_colorcode_line_number = 'false';
	}
	$wp_colorcode_js_url = plugins_url('/js/wp-colorcode.js?ver=20111206', __FILE__);
?>
	<!--colorcode_start-->
	<script type="text/javascript" src="<?php echo $wp_colorcode_js_url; ?>"></script>
	<script type="text/javascript">
		window.onload = function() {
			DlHighlight.HELPERS.highlightByName('colorcode', 'pre','', <?php echo $wp_colorcode_line_number ?>);
		} 
	</script>	
	<!--colorcode_end-->
<?php
}
add_action('wp_footer','wp_colorcode_js');
?>
<?php if(is_admin()){require_once('colorcode_admin.php');} ?>