<?php
function wp_colorcode_admin() {
	add_options_page('ColorCode Options', 'ColorCode','manage_options', __FILE__, 'wp_colorcode_options');
}
function wp_colorcode_options(){
    add_option('wp_colorcode_themes','cc-knight');
	add_option('wp_colorcode_line_number','disable');
?>
<div class="wrap">
	
<?php screen_icon(); ?>
<h2>ColorCode</h2>
<h3>1.Example Usage</h3>
<pre style="background:#e8e8e8">
&lt;pre name="colorcode" class="html" &gt;
	&lt;p&gt;Hello world!&lt;/pr&gt;		
&lt;/pre&gt;
</pre>
<p>The class can be "html", "js", "css" or "xml".</p>
<h3>2.Change Style</h3>
<form action="options.php" method="post" enctype="multipart/form-data" name="wp_colorcode_form">
<?php wp_nonce_field('update-options'); ?>

<table class="form-table">
	<tr valign="top">
		<th scope="row">
			<?php _e('ColorCode Themes','WP-ColorCode'); ?>
		</th>
		<td>
			<label>
				<input name="wp_colorcode_themes" type="radio" value="cc-knight"<?php if (get_option('wp_colorcode_themes') == 'cc-knight') { ?> checked="checked"<?php } ?> />
				knight
			</label>
			<label>
				<input name="wp_colorcode_themes" type="radio" value="cc-moonfang"<?php if (get_option('wp_colorcode_themes') == 'cc-moonfang') { ?> checked="checked"<?php } ?> />
				moonfang
			</label>
			<label>
				<input name="wp_colorcode_themes" type="radio" value="cc-nortrom"<?php if (get_option('wp_colorcode_themes') == 'cc-nortrom') { ?> checked="checked"<?php } ?> />
				nortrom
			</label>
			<label>
				<input name="wp_colorcode_themes" type="radio" value="cc-yurnero"<?php if (get_option('wp_colorcode_themes') == 'cc-yurnero') { ?> checked="checked"<?php } ?> />
				yurnero
			</label>
			<label>
				<input name="wp_colorcode_themes" type="radio" value="random"<?php if (get_option('wp_colorcode_themes') == 'random') { ?> checked="checked"<?php } ?> />
				random
			</label>
		</td>
	</tr>
	<tr valign="top">
		<th scope="row">
			<?php _e('Line Numbers','WP-ColorCode'); ?>
		</th>
		<td>
			<label>
				<input name="wp_colorcode_line_number" type="radio" value="enable"<?php if (get_option('wp_colorcode_line_number') == 'enable') { ?> checked="checked"<?php } ?> />
				<?php _e('enable','WP-ColorCode'); ?>
			</label>
			<label>
				<input name="wp_colorcode_line_number" type="radio" value="disable"<?php if (get_option('wp_colorcode_line_number') == 'disable') { ?> checked="checked"<?php } ?> />
				<?php _e('disable','WP-ColorCode'); ?>
			</label>
		</td>
	</tr>
</table>

<input type="hidden" name="action" value="update" />
<input type="hidden" name="page_options" value="wp_colorcode_themes,wp_colorcode_line_number" />

<p class="submit">
<input type="submit" class="button-primary" name="Submit" value="<?php _e('Save Changes'); ?>" />
</p>

</form>
<h3>3.Contact Author</h3>
<p>Website: <a href="http://www.pizn.net" title="pizn">PIZn</a></p>
<p>Email: <a href="mailto:pizner@gmail.com" title="pizn's mail">PIZn's Mail</a></p>

<?php 
}
add_action('admin_menu', 'wp_colorcode_admin');
?>