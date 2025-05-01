import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import SuiviProductionAdminTabNavigation from '../../../../../../navigation/admin/supply/SuiviProductionAdminTabNavigation';

const SuiviProductionAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <SuiviProductionAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default SuiviProductionAdmin;
