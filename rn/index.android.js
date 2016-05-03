'use strict';

var MOVIE_LIST = [{title:"《美国队长3》",desc:"绝对给力的漫威动画片",thumbnial:"http://i.imgur.com/UePbdph.jpg"}]
var REQUEST_URL = 'https://raw.githubusercontent.com/facebook/react-native/master/docs/MoviesExample.json';

var ToastAndroid2 = require('./ToastAndroid');

import React, {
  Text,
  View,
  Image,
  ListView,
  TouchableHighlight,
} from 'react-native';

class ReactNativeProject extends React.Component {
  constructor(props) {
    super(props);
  
    this.state = {
      movies : null,
      dataSource: new ListView.DataSource({
        rowHasChanged: (row1, row2) => row1 !== row2,
      }),      
      loaded : false
    };
  }

  // componentDidMount(){
  //   this.fetchData();
  // }

  fetchData() {
    fetch(REQUEST_URL).then((response) => response.json()).then((responseData) => {
        this.setState({
          dataSource: this.state.dataSource.cloneWithRows(responseData.movies),
          loaded: true,        
        });
      }).done();
  }

  render() {
    return this.onPressView();
    // if (!this.state.loaded) {
    //   return this.renderingView();
    // }

    // var movie = MOVIE_LIST[0];
    // var movie = this.state.movies[0];

    // return (
    //   <View style={styles.container}>
    //     <ListView dataSource={this.state.dataSource} style={styles.listView}
    //       renderRow={this.renderMovie}
    //     />
    //   </View>
    //   );

    // return (
    //   <View style={styles.container}>
    //     <Text style={styles.hello}>Hello, World</Text>
    //     <Text>测试ReactNative</Text>
    //     <View style={styles.view_horizontal}>
    //       <Image style={styles.image_style} source={{uri: movie.posters.thumbnail}}/>
    //       <Text style={styles.textDetail}>
    //           {movie.title}
    //       </Text>
    //     </View>
    //   </View>
    // )
  }

  onPressView() {
    return(
        <View style={styles.container}>
          <TouchableHighlight onPress={this.onPressBtn}>
          <Text style={styles.textDetail}>
            你点我啊
          </Text>
          </TouchableHighlight>
        </View>
      );
  }

  onPressBtn (){
    ToastAndroid2.show("你好啊",ToastAndroid2.short);
  }

  renderingView() {
    return (
        <View style={styles.container}>
          <Text>
            正在加载电影数据...
          </Text>
        </View>
      );
  }

  renderMovie(movie){
    return(
        <View style={styles.view_horizontal}>
          <Image style={styles.image_style} source={{uri: movie.posters.thumbnail}}/>
          <Text style={styles.textDetail}>
              {movie.title}
          </Text>
        </View>
      );
  }
}
var styles = React.StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  view_horizontal:{
    flexDirection: 'row'
  },
  image_style:{
    width: 30,
    height: 30
  },
  textDetail: {
    fontSize: 15,
    color: 'red',
    width:100,
    height:50,
    justifyContent: 'center'
  },
  listView: {
    paddingTop : 20,
    backgroundColor : '#F5FCFF'
  }
});

React.AppRegistry.registerComponent('ReactNativeProject', () => ReactNativeProject);