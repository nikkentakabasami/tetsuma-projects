#web.tm.js.tsc-<m.entity>-journal.js


import { TetSlickGridBasePagedView } from '../tetSlick/tet.slick.paged.view.js';
import {checkmarkFormatter, mkColDesc, mkSortColDesc, mkExpSortColDesc, nameFormatter} from '../tetSlick/tet.slick.grid.misc.js';
import {tableEvents} from '../tetSlick/tet.slick.grid.events.js';
import {initMultiselect} from '../tetSlick/tet.slick.grid.multiselect.js';

	
var columns = [
	
		<m.propertyList:{f| mkColDesc("<f.propertyName>","<f.propertyName>",150),  
		}>
	
		
];

let options = {
	
	enableColumnReorder: true,
	showHeaderRow: true,
	showTitleHeader: false,

	enableHeaderRowFilters: true,
	multiRowSelectionModel: true,

	pageUrl: "v3/get<m.entity>Json/",
	updateFilterUrl: "v3/update<m.entity>Filter",
	clearFilterUrl: "v3/clear<m.entity>Filter",
	colCookieName: "tcs_<m.entity>_cols",


};


$(document).ready(function() {
	
	let baseGridView = new TetSlickGridBasePagedView("#myGrid", columns, options);
	window.baseGridView = baseGridView;
	

});


