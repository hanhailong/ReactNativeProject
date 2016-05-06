'use strict'

import {
	PropTypes,
} from 'react';

import {
		requireNativeComponent,
	View
} from 'react-native';

var iface = {
	name:"CusImageView",
	propTypes:{
		imageToast:PropTypes.string,
		source:PropTypes.oneOfType([
			PropTypes.shape({
				uri:PropTypes.string,
			}),
			]),
		...View.propTypes,
	}
}

module.exports = requireNativeComponent('CustomImageView',iface);