

let $jstree1;
let $jstree2;

let currentTree

let jsonTreeData1 = [
  'Simple root node',
  {
    'id': 'node_2',
    'text': 'Root node with options',
    'state': { 'opened': true, 'selected': true },
    'children': [
      { 'text': 'Child 1', 'id': 'node_3', },
      'Child 2'
    ]
  }
]

let jsonTreeData2 = [
  { "id": "ajson1", "parent": "#", "text": "Simple root node" },
  { "id": "ajson2", "parent": "#", "text": "Root node 2" },
  { "id": "ajson3", "parent": "ajson2", "text": "Child 1" },
  { "id": "ajson4", "parent": "ajson2", "text": "Child 2" },
];





let selectorsData1 = {


  init1() {

    //задание всех данных целиком через json
    $jstree1.jstree({
      'core': {
        'data': jsonTreeData1
      }
    });

    //дерево, у которого все узлы заданы изначально в html
    $jstree2.jstree();

    le("jsonTreeData1");

  },

  init2() {

    //задание всех данных целиком через json
    //альтернативный json формат
    $jstree1.jstree({
      'core': {
        'data': jsonTreeData2
      }
    });
    le("jsonTreeData2");
		
		currentTree = $jstree1.jstree(true);
		$btn3.text("show_all()");
		$btn3.click(() => {
		  currentTree.show_all();
		});		
		
  },


  init3() {

    //данные для дерева будут передаваться через ajax в json-формате
    //JSTreeServlet
    $jstree1.jstree({
      'core': {
        'data': {
          'url': '../../jsTree/getNode2',
          'data': function(node) {
            return { 'id': node.id };
          }
        }
      }
    });

    la("../../jsTree/getNode2?id=tn1", "пример ссылки для получения данных");
		
  },


  init4() {

    //данные для дерева будут передаваться через ajax в html-формате
    //jstreeDemo1Nodes.jsp

    $jstree1.jstree({
      'core': {
        'data': {
          'url': '../demos_300_jsp/jstreeDemo1Nodes.jsp',
          'data': function(node) {
            return { 'id': node.id };
          }
        }
      }
    });
    la("../../demos/demos_300_jsp/jstreeDemo1Nodes.jsp?id=tn1", "пример ссылки для получения данных через jstreeDemo1Nodes.jsp");


  },
  init5() {

    //checkbox plugin
    $jstree1.jstree({
      "checkbox": {
        "keep_selected_style": false
      },
      "plugins": ["checkbox"],
      'core': {
        'data': {
          'url': '../../jsTree/getNode2',
          'data': function(node) {
            return { 'id': node.id };
          }
        }
      }
    });
  },
	
	init7() {
		//# методы jstree
		$jstree1.jstree({
		  'core': {
		    'data': {
		      'url': '../../jsTree/getNode2',
		      'data': function(node) {
		        return { 'id': node.id };
		      }
		    }
		  }
		});
		currentTree = $jstree1.jstree(true);
		
		$btn1.text("выбрать tn3");
		$btn1.click(() => {
		  //выбор узла
		  currentTree.select_node('tn3');
		  //или можно выбрать так:
		  //$jstree1.jstree('select_node', 'tn3');
		});

		//Открытие узла
		$btn2.text("открыть tn2");
		$btn2.click(() => {
		  currentTree.open_node('tn1', () => {
		    currentTree.open_node('tn2');
		  });
		});


		
	},	
	
	init8() {

	  $jstree1.jstree({
	    'core': {
	      'data': {
	        'url': '../../jsTree/getNode2',
	        'data': function(node) {
	          return { 'id': node.id };
	        }
	      }
	    }
	  });
		currentTree = $jstree1.jstree(true);
		
		//добавление узла на сервер
		$btn1.text("добавить узел");
		$btn1.click(()=>{
			let selectedIds = currentTree.get_selected();
			if (selectedIds.length==0){
				return;
			}
			
			let parentId = selectedIds[0];
			
			$.post({
				url : '../../jsTree/addNode1',
				data: { 'parentId': parentId },
			  success: function(data, status, request){
					currentTree.refresh_node(parentId);
					currentTree.open_node(parentId);
			  },
			});		

		});		
		
		
	},	
	

};



function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_SINGLE_LOG,
    workPanelTemplate: "../fragments/jstreeSandbox.html",
    selectorsData: selectorsData1,
    reloadSandboxOnChange: true,
    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,

    afterSandboxReload: () => {
      $jstree1 = $('#jstree1');
      $jstree2 = $('#jstree2');

      new TabbedPanel("#tabbedPanel2");

    },


    initFunction: () => {


    }
  };
}


function destroyTree1() {
  let tree = $jstree1.jstree(true);

  if (tree) {
    tree.destroy();
  }

}

