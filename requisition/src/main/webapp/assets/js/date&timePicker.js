
/*Datepicker*/
(function( $ ) {

	'use strict';

	if ( $.isFunction($.fn[ 'datepicker' ]) ) {

		$(function() {
			$('[data-plugin-datepicker]').each(function() {
				var $this = $( this ),
					opts = {};
				var pluginOptions = $this.data('plugin-options');
				if (pluginOptions)
					opts = pluginOptions;

				$this.themePluginDatePicker(opts);
			});
		});

	}

}).apply(this, [ jQuery ]);

(function(theme, $) {

	theme = theme || {};
	var instanceName = '__datepicker';

	var PluginDatePicker = function($el, opts) {
		return this.initialize($el, opts);
	};

	PluginDatePicker.defaults = {
	};

	PluginDatePicker.prototype = {
		initialize: function($el, opts) {
			if ( $el.data( instanceName ) ) {
				return this;
			}

			this.$el = $el;

			this
				.setVars()
				.setData()
				.setOptions(opts)
				.build();

			return this;
		},

		setVars: function() {
			this.skin = this.$el.data( 'plugin-skin' );

			return this;
		},

		setData: function() {
			this.$el.data(instanceName, this);

			return this;
		},

		setOptions: function(opts) {
			this.options = $.extend( true, {}, PluginDatePicker.defaults, opts );

			return this;
		},

		build: function() {
			this.$el.datepicker( this.options );
			if(!this.$el.val() && !this.$el.prop('disabled') && this.$el.attr("data-plugin-datepicker-notToday") == undefined){
				this.$el.datepicker('setDate', new Date());
			}
			if ( !!this.skin ) {
				this.$el.data('datepicker').picker.addClass( 'datepicker-' + this.skin );
			}

			return this;
		}
	};

	// expose to scope
	$.extend(theme, {
		PluginDatePicker: PluginDatePicker
	});

	// jquery plugin
	$.fn.themePluginDatePicker = function(opts) {
		return this.each(function() {
			var $this = $(this);

			if ($this.data(instanceName)) {
				return $this.data(instanceName);
			} else {
				return new PluginDatePicker($this, opts);
			}

		});
	}

}).apply(this, [ window.theme, jQuery ]);
	
/* 
TimePicker
*/
(function( $ ) {

	'use strict';

	if ( $.isFunction($.fn[ 'timepicker' ]) ) {

		$(function() {
			$('[data-plugin-timepicker]').each(function() {
				var $this = $( this ),
					opts = {};

				var pluginOptions = $this.data('plugin-options');
				if (pluginOptions)
					opts = pluginOptions;

				$this.themePluginTimePicker(opts);
			});
		});

	}

}).apply(this, [ jQuery ]);

(function(theme, $) {

	theme = theme || {};
	
	var instanceName = '__timepicker';

	var PluginTimePicker = function($el, opts) {
		return this.initialize($el, opts);
	};

	PluginTimePicker.defaults = {
		disableMousewheel: true
	};

	PluginTimePicker.prototype = {
		initialize: function($el, opts) {
			if ( $el.data( instanceName ) ) {
				return this;
			}

			this.$el = $el;

			this
				.setData()
				.setOptions(opts)
				.build();

			return this;
		},

		setData: function() {
			this.$el.data(instanceName, this);

			return this;
		},

		setOptions: function(opts) {
			this.options = $.extend( true, {}, PluginTimePicker.defaults, opts );

			return this;
		},

		build: function() {
			this.$el.timepicker( this.options );

			return this;
		}
	};

	// expose to scope
	$.extend(theme, {
		PluginTimePicker: PluginTimePicker
	});

	// jquery plugin
	$.fn.themePluginTimePicker = function(opts) {
		return this.each(function() {
			var $this = $(this);

			if ($this.data(instanceName)) {
				return $this.data(instanceName);
			} else {
				return new PluginTimePicker($this, opts);
			}

		});
	}

}).apply(this, [ window.theme, jQuery ]);